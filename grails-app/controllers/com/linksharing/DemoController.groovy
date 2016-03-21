package com.linksharing

import com.ttnd.linksharing.CustomBean
import com.ttnd.linksharing.User
import grails.util.Holders
import org.springframework.beans.factory.annotation.Autowired


class DemoController {

    def userService
    def myBean
    def myBeanUsingConstructor
    def mailService

    @Autowired
    CustomBean myBean1

    def index() {
        render "${myBeanUsingConstructor.properties}"
    }

   def mailTry(){
       mailService.sendMail{
           to "surabhigoyal91@gmail.com"
           subject "Hello Fred"
           body 'How are you?'
       }
   }


    def customBeanDemo(){
        def c= Holders.applicationContext.getBean('myBean')
        render("${c}:::c${c.properties}")
    }

    def sumAction(int a, int b){
      int sum = userService.demoMethod(a,b)
        render sum
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
