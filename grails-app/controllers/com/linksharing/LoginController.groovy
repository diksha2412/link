package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.TopicVO
import com.ttnd.linksharing.User

class LoginController {

    def index() {

        if (session.userId) {
            forward(controller: 'User', action: 'index')
        } else {
            //render "failure, no user session"
            List<Resource> resources = ResourceRating.topPosts()
            //println resources
            List<TopicVO> recentShares = Resource.list(max: 2, sort: 'dateCreated', order: 'desc')
            render view: 'home', model: [resources: resources, recentShares: recentShares]
        }
    }

    def login(String userName, String password) {
        User user = User.findByUserNameAndPassword(userName, password)
        if (user) {
            if (user.active) {
                session.userId = user.id
                //session.user = user

                redirect(action: 'index')
            } else {
                flash.error = 'Your account is not active'
            }
        } else {
            flash.error = 'User not found'
            render flash.error
        }
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

//    def test() {
//        User user = new User(firstName: "diksha", lastName: "ahuja", email: "hcdghs@gmail.com", password: "test123",
//                confirmPassword: "test456")
//        if (user.validate()) {
//            render 'true'
//        } else {
//            List errors = []
//            user.errors.allErrors.each { errors << message(error: it) }
//            render errors.join(',')
//        }
//    }

}
