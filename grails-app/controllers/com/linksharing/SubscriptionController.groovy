package com.linksharing

import com.enums.Seriousness
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import grails.converters.JSON

class SubscriptionController {

    def index() { render "subscription" }

    def delete(Long topicId) {

        Map jsonResponseMap = [:]

        println "===================inside subscription delete"

        println "====topic id is: ${topicId}"
        println "====user id is: ${session.userId}"
        Subscription subscription = Subscription.findByUserAndTopic(User.get(session.userId), Topic.get(topicId))

        if (subscription) {
            subscription.delete(flush: true)
            println "================subscription deleted successfully"

            flash.message = "subscription deleted successfully"
            jsonResponseMap.message = "subscription deleted successfully"

        } else {
            flash.error ="error in deleting subscription"
            jsonResponseMap.error ="error in deleting subscription"
        }
        JSON jsonResponse=jsonResponseMap as JSON
        render jsonResponse
    }

    def save(Long topicId) {
        Map jsonResponse = [:]
        println "============inside subscription save"
        User user = User.get(session.userId)
        Subscription subscription = new Subscription(topic: Topic.get(topicId), user: user)
        if (subscription.validate()) {
            subscription.save()
            flash.message="subscription saved successfully"
            jsonResponse.message="subscription saved successfully"
        } else {
            println "===============error in saving subscription"
            println(subscription.errors)
            flash.error="error in saving subscription"
            jsonResponse.error="error in saving subscription"
        }
        render jsonResponse as JSON
    }

    def update(Long id, String serious) {
        Map jsonResponse = [:]
        Subscription subscription = Subscription.get(id)
        if (subscription) {
            subscription.seriousness = Seriousness.convert(serious)
            if (subscription.validate()) {
                subscription.save()
                flash.message="subscription updated successfully"
                jsonResponse.message="subscription updated successfully"
            } else {
                flash.error="error in updating subscription"
                jsonResponse.error="error in updating subscription"
            }
        } else {
            flash.error="subscription not found"
            jsonResponse.error="subscription not found"
        }
        render jsonResponse as JSON
    }
}
