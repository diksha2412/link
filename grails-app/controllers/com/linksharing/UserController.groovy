package com.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.co.TopicSearchCO
import com.ttnd.linksharing.co.UserCO
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.Topic
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

    /*def update(Long id, Boolean isRead) {
        if (ReadingItem.changeIsRead(id, isRead)) {
            render 'success'
        } else {
            render 'error'
        }
    }
*/
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
        println "=====inside user edit======"
        println "=======${userCO.properties}"
        User user = User.get(session.userId)

        user.firstName=userCO.firstName
        user.lastName=userCO.lastName
        user.userName=userCO.userName

//        List<Topic> topicsCreated = user.topics

        if (!params.photo.empty){
            user.photo=params.photo.bytes
        }

        if (user.save(flush: true)){
            flash.message="details updated successfully"
            redirect(controller: 'login', action: 'index')
        } else {
            flash.error="error in updating details"
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

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: resourceSearchCO.id, visibility: resourceSearchCO.visibility)

        List<Resource> resources = resourceService.search(resourceSearchCO)
        List<Resource> subscribedTopics = subscriptionService.search(topicSearchCO)
        List<Topic> topicsCreated = topicService.search(topicSearchCO)

        UserVO userDetails = User.get(session.userId).getUserDetails()
//        User user = User.get(resourceSearchCO.id)

        render view: 'profile', model: ['resources'  : resources, 'topicsCreated': topicsCreated,
                                        'userDetails': resourceSearchCO.getUser(), subscribedTopics: subscribedTopics]
    }


    def showEditProfile() {
        User user = User.get(session.userId)
        render(view: '/user/edit', model: ['user': user])
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
