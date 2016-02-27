package com.linksharing

import com.enums.Seriousness
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

class SubscriptionController {

    def index() {}

    void delete(Long id) {
        Subscription subscription = Subscription.get(id)
        if (subscription) {
            subscription.delete()
            render("success")
        } else {
            render("not found")
        }
    }

    void save(Long topicId){
        User user=User.get(session.userId)
        Subscription subscription=new Subscription(Topic.get(topicId), user)
        if (subscription.validate()){
            subscription.save()
            render("subscription saved successfully")
        } else {
            println(subscription.errors)
        }
    }

    void update(Long id, Seriousness serious){
        Subscription subscription=Subscription.get(id)
        if (subscription){
            subscription.seriousness=serious
            if (subscription.validate()){
                subscription.save()
                render "success"
            } else {
                render subscription.errors
            }
        } else {
            render 'not found'
        }
    }
}
