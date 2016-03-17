package com.linksharing

import com.enums.Seriousness
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
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

        Topic topic = Topic.get(topicId)
        User user = User.get(session.userId)

        if (!user.equals(topic.createdBy)) {
            Subscription subscription = Subscription.findByUserAndTopic(user, topic)

            if (subscription) {
                subscription.delete(flush: true)
                println "================subscription deleted successfully"

//            flash.message = "subscription deleted successfully"
                jsonResponseMap.message = "subscription deleted successfully"

            } else {
//            flash.error ="error in deleting subscription"
                jsonResponseMap.error = "error in deleting subscription"
            }
        } else {
            flash.message = "creator of the topic cannot unsubscribe"
        }

        JSON jsonResponse = jsonResponseMap as JSON
        render jsonResponse
    }

    def save(Long topicId) {
        Map jsonResponse = [:]
        User user = User.get(session.userId)
        Topic topic = Topic.get(topicId)
        Subscription subscription = new Subscription(topic: topic, user: user)
        if (subscription.validate()) {
            subscription.save()

            topic.resources.each { Resource resource ->
                user.addToReadingItems(new ReadingItem(user: user, resource: resource, isRead: false).save(failOnError: true))
            }
            flash.message = "subscription saved successfully"
            jsonResponse.message = "subscription saved successfully"
        } else {
            println(subscription.errors)
            flash.error = "error in saving subscription"
            jsonResponse.error = "error in saving subscription"
        }
        render jsonResponse as JSON
    }

    def update(Long topicId, String serious) {
        println "=====inside update======"
        Map jsonResponse = [:]
        Subscription subscription = Subscription.findByUserAndTopic(User.get(session.userId), Topic.get(topicId))
        if (subscription) {
            println "====subs found"
            subscription.seriousness = Seriousness.convert(serious)
            if (subscription.validate()) {
                println "====subs validated"
                subscription.save()
                println "======subs successfully saved"
                flash.message = "subscription updated successfully"
                jsonResponse.message = "subscription updated successfully"
            } else {
                println "=====error in updating subscription"
                flash.error = "error in updating subscription"
                jsonResponse.error = "error in updating subscription"
            }
        } else {
            println "===========subs not found"
            flash.error = "subscription not found"
            jsonResponse.error = "subscription not found"
        }
        render jsonResponse as JSON
    }
}
