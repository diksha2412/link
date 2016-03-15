package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.dto.EmailDTO
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import grails.converters.JSON

class TopicController {

    def emailService

    def index() {}

    def show(ResourceSearchCO resourceSearchCO) {
        Topic topic = Topic.get(resourceSearchCO.topicId)
        if (!topic) {
            flash.error = "no topic in database"
            redirect(controller: 'user', action: 'index')
        } else if (topic.visibility == Visibility.PUBLIC) {
            render view: '/topic/show', model: [topic: topic, users: topic.getSubscribedUsers()]
        } else {
            User user1 = User.findByUserName(session.userId)
            if (Subscription.findByTopicAndUser(topic, user1)) {
                render 'success'
            } else {
                flash.error = "user subscription doesn't exist for the given topic"
                redirect(controller: 'user', action: 'index')
            }
        }
    }

//    def show(ResourceSearchCO resourceSearchCO){
//        render 'implementing named query to retrieve resources particular to topic id'
//        List<Resource> resources=Resource.search(resourceSearchCO).list()
//        render resources
//    }


    def invite(Long topic, String email) {
        Topic topic1 = Topic.get(topic)

        String to = email
        String subject = "Invitation for a new topic."
        String hostURL = grailsApplication.config.grails.serverURL

        EmailDTO emailDTO = new EmailDTO(to: to, subject: subject, model: [id: topic, hostURL: hostURL])

        if (topic1 == null)
            flash.error = "Topic could not be found."
        else {
            emailService.sendMail(emailDTO)
            flash.message = "Email sent"
        }

        redirect(controller: "login", action: "index")
    }

    def join(Long id) {
        if (session.userId) {

            User user = User.get(session.userId)
            Topic topic = Topic.get(id)
            Subscription subscription = new Subscription(user: user, topic: topic)

            if (subscription.save())
                flash.message = "You have subscribed to this topic successfully."
            else
                flash.error = "Failure. Could not subscribe to the topic."

            redirect(controller: "login", action: "index")
        }
    }

    def save(String name, String visibility) {
        println "======inside topic save"
        Topic topic1 = new Topic(name: name, createdBy: User.get(session.userId), visibility: Visibility.convert(visibility))

        if (topic1.validate()) {
            println "1"
            topic1.save()
            println "2"
            flash.message = 'topic saved successfully'
            redirect controller: 'user', action: 'index'
        } else {
            render "failure"
            log.error('error in saving the topic')
            flash.error = topic1.errors
            render(flash.error)
        }
    }

    def delete(Long topicId) {
        Map jsonResponseMap = [:]
        Topic topic = Topic.get(topicId)
        if (topic.delete(flush: true)){
            jsonResponseMap.message = "topic deleted successfully"
        } else {
            jsonResponseMap.error ="error in deleting topic"
        }
        JSON jsonResponse=jsonResponseMap as JSON
        render jsonResponse
    }
}
