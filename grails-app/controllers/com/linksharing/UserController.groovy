package com.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.CO.TopicSearchCO
import com.ttnd.linksharing.CO.UserCO
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.VO.TopicVO
import com.ttnd.linksharing.User
import com.ttnd.linksharing.VO.UserVO

class UserController {

    def assetResourceLocator
    def topicService
    def resourceService
    def subscriptionService

    def index() {
        //render User.get(session.userId as Long).fullName
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        User user = User.get(session.userId)
        def readingItemsList = user.readingItems
//        println readingItemsList
        render view: 'dashboard', model: ['subscribedTopics': User.get(session.userId).subscribedTopics,
                                          'trendingTopics'  : trendingTopics, 'readingItems': readingItemsList,
                                          'subscriptions'   : User.get(session.userId).subscriptions, 'user': user]

    }

    def update(Long id, Boolean isRead) {
        if (ReadingItem.changeIsRead(id, isRead)) {
            render 'success'
        } else {
            render 'error'
        }
    }

    def register(UserCO co) {
        render "inside register"
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

    def profile(ResourceSearchCO resourceSearchCO) {

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: resourceSearchCO.id, visibility: resourceSearchCO.visibility)

        List<Resource> resources = resourceService.search(resourceSearchCO)
        List<Resource> subscribedTopics = subscriptionService.search(topicSearchCO)
        List<Topic> topicsCreated = topicService.search(topicSearchCO)

        UserVO userDetails = User.get(session.userId).getUserDetails()
//        User user = User.get(resourceSearchCO.id)

        render view: 'profile', model: ['resources': resources, 'topicsCreated': topicsCreated,
                                        'userDetails': resourceSearchCO.getUser(), subscribedTopics: subscribedTopics]

    }

    def edit(){
        render "inside edit"
//        render view: '/user/edit'
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
