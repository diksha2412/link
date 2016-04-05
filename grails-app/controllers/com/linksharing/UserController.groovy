package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.co.TopicSearchCO
import com.ttnd.linksharing.co.UserCO
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.co.UserSearchCO
import com.ttnd.linksharing.vo.TopicVO
import com.ttnd.linksharing.User
import com.ttnd.linksharing.vo.UserVO
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    def assetResourceLocator
    def topicService
    def resourceService
    def subscriptionService
    def mailService
    def springSecurityService

    /*def index() {
        if (springSecurityService.isLoggedIn()) {
            User user = User.read(springSecurityService.currentUserId as Long)
            session.userId=user.id
            springSecurityService.reauthenticate(user.email)
            List<TopicVO> trendingTopics = Topic.getTrendingTopics()
            List<ReadingItem> readingItemsList = ReadingItem.getReadingItems(user)
            flash.message = "Login successful..!!"
            render(view: '/user/dashboard', model: ['subscribedTopics': User.get(session.userId).subscribedTopics,
                                                     'trendingTopics'  : trendingTopics,
                                                     'readingItems'    : readingItemsList,
                                                     'subscriptions'   : User.get(session.userId).subscriptions,
                                                     'user'            : user])
        } else {
            flash.error = "Unsuccessful Login..!!"
            redirect(controller: 'login', action: 'index')
        }
    }
*/

    def index() {
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        User user = User.loggedInUser()
        List<ReadingItem> readingItemsList = ReadingItem.getReadingItems(user)

        render view: 'dashboard', model: ['subscribedTopics': User.get(session.userId).subscribedTopics,
                                          'trendingTopics'  : trendingTopics,
                                          'readingItems'    : readingItemsList,
                                          'subscriptions'   : User.get(session.userId).subscriptions,
                                          'user'            : user]
    }

    def trial() {
        render(template: '/subscription/subscriptions', model: ['subscriptions': User.get(session.userId).subscriptions])
    }

    def register(UserCO co) {
        User user = new User(co.properties)
        if (!params.file.empty) {
            user.photo = params.file.bytes
        }
        if (user.validate()) {
            user.save(flush: true)
            flash.message = "Registration successful."
        } else {
            flash.error = "Unsuccessful Registration. Please try again."
        }
        redirect(controller: 'login', action: 'index')
    }

    def edit(UserCO userCO) {
        User user = User.get(session.userId)

        user.firstName = userCO.firstName
        user.lastName = userCO.lastName
        user.userName = userCO.userName

        if (!params.photo.empty) {
            user.photo = params.photo.bytes
        }

        if (user.save(flush: true, failOnError: true)) {
            flash.message = "details updated successfully"
            redirect(controller: 'login', action: 'index')
        } else {
            flash.error = "error in updating details"
        }
    }


    def image(Long userId) {

        byte[] img
        User user = User.get(userId)

        if (user?.photo)
            img = user.photo
        else
            img = assetResourceLocator.findAssetForURI('user.png').byteArray

        response.outputStream.write(img)
        response.outputStream.flush()
    }

    def showProfile() {
        render view: '/user/profile', model: ['user': User.get(session.userId)]
    }

    def profile(ResourceSearchCO resourceSearchCO) {

        User user = resourceSearchCO.getUser(resourceSearchCO.id)

        if (session.userId) {
            if (!((User.get(session.userId).admin) || (session.userId == id))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else {
            topicSearchCO.visibility = Visibility.PUBLIC
        }
        List<Resource> resources = resourceService.search(resourceSearchCO)
        render(view: '/user/profile', model: ['user': user])
    }

    def topics(Long id) {
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)
        if (session.userId) {
            if (!((User.get(session.userId).admin) || (session.userId == id))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else {
            topicSearchCO.visibility = Visibility.PUBLIC
        }
        List<TopicVO> createdTopics = topicService.search(topicSearchCO)
        render(template: '/user/topics', model: [topics: createdTopics])
    }

    def subscriptions(Long id) {
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)
        if (session.userId) {
            if (!((User.get(session.userId).admin) || (session.userId == id))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else {
            topicSearchCO.visibility = Visibility.PUBLIC
        }
        List<TopicVO> subscribedTopics = subscriptionService.search(topicSearchCO)
        render(template: '/user/subscribedTopics', model: [subscribedTopics: subscribedTopics])
    }


    def showEditProfile() {
        User user = User.get(session.userId)
        List<Topic> topicsCreated = user.topics.toList()
        render(view: '/user/edit', model: ['user': user, 'topicsCreated': topicsCreated])
    }

    def sendForgetPasswordEmail(String emailID) {
        User user = User.findByEmail(emailID)
        if (user) {
            mailService.sendMail {
                to "${user.email}"
                subject "Forgot Password Request"
                body "Hello, This is your password: ${user.password}. Login again with this password."
            }
            flash.message = "success"
        } else {
            flash.error = "Invalid Email ID. Try Again ...!!"
        }
        redirect(controller: 'login', action: 'index')
    }

    def sendInvite(String email, String topic) {
        mailService.sendMail {
            to "${email}"
            subject "invite for subscription"
            body "This is an invite for the topic ${topic}"
        }
        flash.message = "success"
    }

    def changePassword(String oldPwd, String newPwd) {
        User user = User.findByPassword(oldPwd)
        if (user) {
            String query = "update User set password=:password where id=:id"
            if (User.executeUpdate(query, [password: newPwd, id: session.userId])) {
                flash.message = "success"
            } else {
                flash.error = "error"
            }
            session.invalidate()
            redirect(controller: 'login', action: 'index')
        } else {
            flash.error = "password entered is incorrect. Try Again..!!!"
        }
    }

    def list(UserSearchCO userSearchCO) {
        if (session.userId) {
            User user = User.get(session.userId)
            if (user.admin) {
                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max, sort: userSearchCO.sort, order: userSearchCO.order)
                List<UserVO> usersList = users?.collect {
                    user1 ->
                        new UserVO(id: user1.id, userName: user1.userName, email: user1.email, firstName: user1.firstName, lastName: user1.lastName,
                                active: user1.active)
                }
                render(view: "/user/list", model: ['usersList': usersList, totalCount: User.search(userSearchCO).count()])
            } else {
                redirect(controller: 'login', action: 'index')
            }
        } else {
            redirect(controller: 'login', action: 'index')
        }
    }

    def paginate(UserSearchCO userSearchCO) {
        User user = User.get(session.userId)
        if (user.admin) {
            List<User> users = User.search(userSearchCO).list(userSearchCO.properties)
            List<UserVO> usersList = users?.collect {
                user1 ->
                    new UserVO(id: user1.id, userName: user1.userName, email: user1.email, firstName: user1.firstName, lastName: user1.lastName,
                            active: user1.active)
            }
            render(template: 'userList', model: ['usersList': usersList, totalCount: User.search(userSearchCO).count()])
        }
    }

    def toggleActive(Long id) {

        if (session.userId) {
            User sessionUser = User.get(session.userId)

            if (sessionUser.admin) {

                User user = User.get(id)

                if (user) {
                    if (user.admin) {
                        flash.error = "Admin active status cannot be changed."
                    } else {
                        user.active = !(user.active)
                        user.confirmPassword = user.password
                    }

                    if (user.save(flush: true)) {

                        flash.message = "User active status changed"
                    } else
                        flash.error = "User active status could not be changed"
                } else
                    flash.error = "User not found."

                redirect(controller: "user", action: "list")
            } else
                redirect(controller: "login", action: "index")
        } else {
            redirect(controller: "login", action: "index")
        }
    }

    def validatePassword() {
        Boolean result = User.findByPassword(params.oldPwd) ? true : false
        render result
    }
}
