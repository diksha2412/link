package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.User

class UserController {

    def index() {
        render User.get(session.userId as Long).fullName
    }

    def register(){
        User user = new User(firstName: "richa", lastName: "saini", email: "richa.saini@gmail.com", password: "richa123" )
        if(!session.user){
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
