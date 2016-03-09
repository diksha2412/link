package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.TopicVO
import com.ttnd.linksharing.User
import com.ttnd.linksharing.UserCO

class UserController {

    def index() {
        //render User.get(session.userId as Long).fullName
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        def readingItemsList=User.get(session.userId).readingItems
//        println readingItemsList
        render view:'dashboard', model: ['subscribedTopics': User.get(session.userId).subscribedTopics,
                                         'trendingTopics': trendingTopics, 'readingItems': readingItemsList,
                                         'subscriptions' : User.get(session.userId).subscriptions]

    }

    def update(Long id, Boolean isRead){
        if(ReadingItem.changeIsRead(id, isRead)){
            render 'success'
        } else {
            render 'error'
        }
    }

    def register(String firstName, String lastName, String email, String password, String confirmPassword, String userName ) {
        render "inside register"
        User user = new User(firstName: firstName, lastName: lastName, email: email, password: password,
                confirmPassword: confirmPassword, userName: userName)
        if (user.validate()) {
            user.save()
            render 'success'
        } else {
            flash.error = user.errors
            render user.errors
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
