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

class UserController {

    def assetResourceLocator
    def topicService
    def resourceService
    def subscriptionService
    def mailService

    def index() {
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        User user = User.get(session.userId)

        List<ReadingItem> readingItemsList = ReadingItem.createCriteria().list() {
            eq('isRead', false)
            eq('user', user)
        }

        render view: 'dashboard', model: ['subscribedTopics': User.get(session.userId).subscribedTopics,
                                          'trendingTopics'  : trendingTopics, 'readingItems': readingItemsList,
                                          'subscriptions'   : User.get(session.userId).subscriptions, 'user': user]
    }

    def register(UserCO co) {
        User user = new User(co.properties)
        if (!params.file.empty) {
            user.photo = params.file.bytes
        }
        if (user.validate()) {
            user.save(flush: true)
            render 'success'
        } else {
            flash.error = user.errors
            render user.errors
        }
    }

    def edit(UserCO userCO) {
        User user = User.get(session.userId)

        user.firstName = userCO.firstName
        user.lastName = userCO.lastName
        user.userName = userCO.userName

        if (!params.photo.empty) {
            user.photo = params.photo.bytes
        }

        if (user.save(flush: true)) {
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
        println "====inside ForgotPassword====="
        println "====email id entered is : ${emailID}"
        User user = User.findByEmail(emailID)
        if (user) {
            println "===user found"
            mailService.sendMail {
                println "====inside email service"
                to "${user.email}"
                subject "Forgot Password Request"
                body "Hello, This is your password: ${user.password}. Login again with this password."
            }
            flash.message = "success"
        } else {
            flash.error = "user not found"
        }
    }

    def sendInvite(String email, String topic) {
        println "=====inside sendInvite method======"
        mailService.sendMail {
            println "====inside email service"
            to "${email}"
            subject "invite for subscription"
            body "This is an invite for the topic ${topic}"
        }
        flash.message = "success"
    }

    def changePassword(String pwd, String changePwd) {
        User user = User.findByPassword(pwd)
        if (user) {
            String query = "update User set password=:password where id=:id"
            if (User.executeUpdate(query, [password: changePwd, id: session.userId])) {
//                render "password updated successfully"
                flash.message = "success"
            } else {
                flash.error = "error"
            }
            session.invalidate()
            redirect(controller: 'login', action: 'index')
        }
    }

    def list(UserSearchCO userSearchCO) {
        println "=========inside list=============="
        println "=========${userSearchCO.properties}"
        if (session.userId) {
            User user = User.get(session.userId)
            if (user.admin) {
                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max, sort: userSearchCO.sort, order: userSearchCO.order)
                List<UserVO> usersList = users?.collect {
                    user1 ->
                        new UserVO(id: user1.id, userName: user1.userName, email: user1.email, firstName: user1.firstName, lastName: user1.lastName,
                                active: user1.active)
                }
                render(view: "/user/list", model: ['usersList': usersList])
            } else {
                redirect(controller: 'login', action: 'index')
            }
        } else {
            redirect(controller: 'login', action: 'index')
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

    /*def getScore(Long resourceId, Integer score) {
        String msg =""
        User user = User.get(session.user)
        Resource resource = Resource.findById(resourceId)
        println resource
        if(resource) {
            ResourceRating.executeUpdate("update ResourceRating r set r.score=:score where " +
                    "r.resource.id=:resourceId and r.user.id = :userId", [score: score, resourceId: resourceId, userId: user.id])
            msg = "Sucess"
        }else{
            msg = "${resource} not found."
        }
        render msg
    }*/
}
