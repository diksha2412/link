package com.linksharing

import com.ttnd.linksharing.User

class DemoController {

    def index() { }

    def myFirstAction(){
        List<User> users=User.list()
        render view: 'try', model: [users: users]
    }

    def testForm(){
        render view: 'form'
    }

    
}
