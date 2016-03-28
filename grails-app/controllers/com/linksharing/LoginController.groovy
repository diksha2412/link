package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User
import grails.converters.JSON

class LoginController {

    def index() {
        if (session.userId) {
            forward(controller: 'User', action: 'index')
        } else {
            List<Resource> resources = Resource.showTopPosts()
            List<Resource> recentShares = Resource.showRecentShares()
            render view: 'home', model: [resources: resources, recentShares: recentShares]
        }
    }

    def login(String userName, String password) {
        Map jsonResponseMap = [:]
        User user = User.findByUserNameAndPassword(userName, password)
        if (user) {
            if (user.active) {
                session.userId = user.id
                jsonResponseMap.message = "Login successful.(JSON) "
                flash.message = "Login successful."
                forward(action: 'index')
            } else {
                jsonResponseMap.error="Your account is not active(JSON)"
                flash.error = "Your account is not active"
            }
        } else {
            jsonResponseMap.error="Either user name or password is incorrect(JSON)"
            flash.error = "Either user name or password is incorrect"
            redirect(action: 'index')
        }
        JSON jsonResponse = jsonResponseMap as JSON
        render(jsonResponse)
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
