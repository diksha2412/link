package com.linksharing

import com.ttnd.linksharing.User

class DemoController {

    def index() {
        render view: 'form'

    }

    def myFirstAction(){
        List<User> users=User.list()
        render view: 'try', model: [users: users]
    }

    def testForm(){
        render view: 'form'
    }

    def save(User user){
        if(user?.hasErrors()){
            render view: 'form', model: [user: user ]
        }else {
            user.save()
            render 'user saved'
        }
    }

    def taglib(){
        render view: 'try'
    }
}
