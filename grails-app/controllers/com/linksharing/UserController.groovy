package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.Role
import com.ttnd.linksharing.UserRole
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

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class UserController {

    def assetResourceLocator
    def topicService
    def resourceService
    def subscriptionService
    def mailService
    def springSecurityService
    def utilService

    def index() {
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        session.userId = springSecurityService.currentUserId as Long
        User user = User.get(session.userId)
        List<ReadingItem> readingItemsList = ReadingItem.getReadingItems(user)

        render view: 'dashboard', model: ['subscribedTopics': user.subscribedTopics,
                                          'trendingTopics'  : trendingTopics,
                                          'readingItems'    : readingItemsList,
                                          'subscriptions'   : user.subscriptions,
                                          'user'            : user]
    }

    def trial() {
        render(template: '/subscription/subscriptions', model: ['subscriptions': User.get(session.userId).subscriptions])
    }


    @Secured(['permitAll'])
    def register(UserCO co) {
        println ">>>>>>>>>inside user/register"
        println "${co.properties}"
        User user = new User(co.properties)
        user.password = utilService.fetchEncodedPassword(co.password)
        if (!params.file.empty) {
            user.photo = params.file.bytes
        }
        if (user.validate()) {
            user.save(flush: true)
            UserRole.create(user, Role.findByAuthority('ROLE_ADMIN'), true)
            flash.message = "Registration successful."
        } else {
            flash.error = "Unsuccessful Registration. Please try again."
        }
        redirect(controller: 'login', action: 'index')
    }

    def edit(UserCO userCO) {
        println ">>>>>>inside user/edit"
        println "${userCO.properties}"
        User user = User.get(session.userId)

        user.firstName = userCO.firstName
        user.lastName = userCO.lastName
        user.username = userCO.username
        println "${user.firstName}"
        println "${user.lastName}"
        println "${user.username}"

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


    @Secured(['permitAll'])
    def image(Long id) {
        User user = User.read(id)
        if (user && user?.photo) {
            byte[] img = user.photo
            response.outputStream.write(img)
        } else {
            response.outputStream << assetResourceLocator.findAssetForURI('user.png').getInputStream()
        }
        response.outputStream.flush()
    }

    def showProfile() {
        render view: '/user/profile', model: ['user': User.get(session.userId)]
    }

    def profile(ResourceSearchCO resourceSearchCO) {

        User user = resourceSearchCO.getUser(resourceSearchCO.id)

        if (session.userId) {
            if (!((User.get(session.userId).isAdmin()) || (session.userId == id))) {
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
            if (!((User.get(session.userId).isAdmin()) || (session.userId == id))) {
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
            if (!((User.get(session.userId).isAdmin()) || (session.userId == id))) {
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
            if (user.isAdmin()) {
                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max, sort: userSearchCO.sort, order: userSearchCO.order)
                List<UserVO> usersList = users?.collect {
                    user1 ->
                        new UserVO(id: user1.id, username: user1.username, email: user1.email, firstName: user1.firstName, lastName: user1.lastName)
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
        if (user.isAdmin()) {
            List<User> users = User.search(userSearchCO).list(userSearchCO.properties)
            List<UserVO> usersList = users?.collect {
                user1 ->
                    new UserVO(id: user1.id, userName: user1.username, email: user1.email, firstName: user1.firstName, lastName: user1.lastName)
            }
            render(template: 'userList', model: ['usersList': usersList, totalCount: User.search(userSearchCO).count()])
        }
    }

    def toggleActive(Long id) {

        if (session.userId) {
            User sessionUser = User.get(session.userId)

            if (sessionUser.isAdmin()) {

                User user = User.get(id)

                if (user) {
                    if (user.isAdmin()) {
                        flash.error = "Admin active status cannot be changed."
                    } else {
                        user.enabled = !(user.enabled)
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
