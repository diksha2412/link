package com.linksharing

import com.ttnd.linksharing.User

class LoginController {

    def index() {

        if (session.userId) {
            forward(controller: 'User', action: 'index')
        } else {
            //render "failure, no user session"
            render view: 'home'
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

    def register(String firstName, String lastName, String email, String password, String confirmPassword, String userName ){
        render "inside register"
        User user = new User(firstName: firstName, lastName: lastName, email: email, password: password,
                confirmPassword: confirmPassword, userName: userName )
        if(user.validate()){
            user.save()
            render 'success'
        } else {
            flash.error=user.errors
            render user.errors
        }
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
