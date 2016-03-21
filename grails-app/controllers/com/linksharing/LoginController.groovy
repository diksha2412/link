package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User

class LoginController {

    def index() {
        if (session.userId) {
            forward(controller: 'User', action: 'index')
        } else {
            List<Resource> resources = Resource.showTopPosts()
            List<Resource> recentShares = Resource.list(max: 5, sort: 'dateCreated', order: 'desc')
            render view: 'home', model: [resources: resources, recentShares: recentShares]
        }
    }

    def login(String userName, String password) {
        println "-----------1----------"
        User user = User.findByUserNameAndPassword(userName, password)
        println "----------${user}--------"
        if (user) {
            if (user.active) {
                println "-----------${user.active}---------"
                session.userId = user.id
                flash.message = "Login successfully."
                forward(action: 'index')
            } else {
                flash.error = "Your account is not active"
            }
        } else {
            flash.error = "User not found"
            redirect(action: 'index')
        }
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

    def validateEmail() {
        User.findByEmail(params.email) ? false : true
    }

    def validateUserName() {
        User.findByUserName(params.userName) ? false : true
    }

}
