package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.Resource
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
        println "=======inside topic show "
        println "=====topic id is : ${resourceSearchCO.topicId}"
        Topic topic = Topic.get(resourceSearchCO.topicId)
        if (!topic) {
            flash.error = "no topic in database"
            redirect(controller: 'user', action: 'index')
        } else if (topic.visibility == Visibility.PUBLIC) {
            render view: '/topic/show', model: [topic: topic, users: topic.getSubscribedUsers()]
        } else {
            User user = User.get(session.userId)
            if (Subscription.findByTopicAndUser(topic, user)) {
                flash.message= 'success'
                render view: '/topic/show', model: [topic: topic, users: topic.getSubscribedUsers()]
            } else {
                flash.error = "user subscription doesn't exist for the given topic"
//                redirect(controller: 'user', action: 'index')
            }
        }
    }

    def showNamedQuery(ResourceSearchCO resourceSearchCO) {
        render 'implementing named query to retrieve resources particular to topic id'
        List<Resource> resources = Resource.search(resourceSearchCO).list()
        render resources
    }


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
            flash.message = "You have subscribed to this topic successfully."
            if (subscription.save(flush: true)) {
                flash.message = "subscription saved successfully"
            } else {
                flash.error = "Failure. Could not subscribe to the topic."
            }
        }
        redirect(controller: "login", action: "index")
    }

    def save(String name, String visibility) {
        Topic topic1 = new Topic(name: name, createdBy: User.get(session.userId), visibility: Visibility.convert(visibility))
        if (topic1.validate()) {
            topic1.save(flush: true)
            flash.message = 'topic saved successfully'
            redirect controller: 'user', action: 'index'
        } else {
            flash.error = topic1.errors
        }
    }

    def titleUpdate(String title, Long topicId) {
        Map jsonResponse =[:]
        Topic topic = Topic.get(topicId)
        if (topic){
            topic.name = title
            if (topic.save(flush: true, failOnError: true)){
                flash.message="title updated successfully"
                jsonResponse.message="title updated successfully(JSON)"
            } else {
                jsonResponse.error="error in saving topic"
                flash.error="error in saving topic"
            }
        } else {
            jsonResponse.error="topic not found(JSON)"
            flash.error="topic not found"
        }
        render jsonResponse as JSON
    }
    
    def update(Long topicId, String visibility){
        Map jsonResponse =[:]
        Topic topic = Topic.get(topicId)
        if (topic){
            topic.visibility=Visibility.convert(visibility)
            topic.save(flush: true)
            flash.message = "visibility changed successfully"
            jsonResponse.message = "visibility changed successfully"
        } else {
            jsonResponse.error="visibility couldn't be changed"
            flash.error="visibility couldn't be changed"
        }
        render jsonResponse as JSON
    }

    def validateName(){
        Boolean result = Topic.findByName(params.name) ? false : true
        render result
    }

    def delete(Long topicId) {
        Map jsonResponseMap = [:]
        Topic topic = Topic.get(topicId)
        if (topic) {
            topic.delete(flush: true)
//            flash.message="topic deleted successfully"
            jsonResponseMap.message = "topic deleted successfully"
        } else {
            println "====2"
//            flash.error="error in deleting topic"
            jsonResponseMap.error = "error in deleting topic"
        }
//        redirect(controller: 'user', action: 'index')
        JSON jsonResponse = jsonResponseMap as JSON
        render jsonResponse
    }
}
