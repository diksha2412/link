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
        User user = User.findByUserNameAndPassword(userName, password)
        if (user) {
            if (user.active) {
                session.userId = user.id
                flash.message = "Login successfully."
                forward(action: 'index')
            } else {
                flash.error = "Your account is not active"
            }
        } else {
            flash.error = "Either user name or password is incorrect"
            redirect(action: 'index')
        }
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

    def validateEmail() {
        Boolean result = User.findByEmail(params.email) ? false : true
        render result
    }

    def validateUserName() {
        Boolean result= User.findByUserName(params.userName) ? false : true
        render result
    }
}
