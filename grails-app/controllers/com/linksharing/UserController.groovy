package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.TopicVO
import com.ttnd.linksharing.User
import com.ttnd.linksharing.UserCO

class UserController {

    def index() {
        //render User.get(session.userId as Long).fullName
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        render view:'dashboard', model: ['subscribedTopics': User.get(session.userId).subscribedTopics,
                                         'trendingTopics': trendingTopics ]
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


}
