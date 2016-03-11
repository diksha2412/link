package com.linksharing

import com.ttnd.linksharing.CO.UserCO
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.VO.TopicVO
import com.ttnd.linksharing.User

class UserController {

    def assetResourceLocator

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
