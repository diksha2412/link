package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.User

class UserController {

    def index() {
        //render User.get(session.userId as Long).fullName
        render view:'dashboard'
    }

    def register(String firstName, String lastName, String email, String password ){
        User user = new User(firstName: firstName, lastName: lastName, email: email, password: password )
        if(!session.userId){
            flash.message(user.not.set)
        } else {
            render 'success'
        }
    }

    def update(Long id, Boolean isRead){
        if(ReadingItem.changeIsRead(id, isRead)){
            render 'success'
        } else {
            render 'error'
        }
    }
}
