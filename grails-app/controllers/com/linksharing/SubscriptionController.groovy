package com.linksharing

import com.enums.Seriousness
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

class SubscriptionController {

    def index() { render "subscription"}

    def delete(Long id) {
        Subscription subscription = Subscription.get(id)
        if (subscription) {
            subscription.delete(flush: true)
            render("success")
        } else {
            render("not found")
        }
    }

    def save(Long topicId){
        User user=User.get(session.userId)
        Subscription subscription=new Subscription(topic: Topic.get(topicId),user: user)
        if (subscription.validate()){
            subscription.save()
            render("subscription saved successfully")
        } else {
            println(subscription.errors)
        }
    }

    def update(Long id, String serious){
        Subscription subscription=Subscription.get(id)
        if (subscription){
            subscription.seriousness=Seriousness.convert(serious)
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
