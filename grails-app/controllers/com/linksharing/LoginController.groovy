package com.linksharing

import com.link.sharing.core.User

class LoginController {

    /* def index() {
        if(session.user){
            forward(controller: 'User', action: 'index')
        }else {
            render "failure"
        }
    }

    def loginHandler(String userName, String password){
        User user=User.findByUserNameAndPassword(userName, password)
        if (user){
            if(user.active){
                session.use=true
                redirect(action: 'index')
            } else {
                flash.error= 'Your account is not active'
            }
        } else {
            flash.error='User not found'
            render 'flash.error'
        }
    } */

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

}
