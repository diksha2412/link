package com.linksharing

import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

class LinkResourceController {

    def index() {}

    def create(String link, String description, Integer topicName) {
        println "==========inside linkresource create"
        println("=======topicId : ${topicName}")
        
        Topic topic = Topic.findById(topicName)
        if (topic){
            println "======${topic.name} found========"
        } else {
            println "topic not found"
        }
        LinkResource linkResource = new LinkResource(url: link, description: description, createdBy: User.get(session.userId),
                topic: topic)
        
        if (linkResource) {
            if (linkResource.validate()) {
                println "========link resource validated=========="
                linkResource.save()
                println "=========saved======="
                topic.addToResources(linkResource)
                println "========added to topic========"
                flash.message="link resource saved successfully"
                redirect(controller: 'user', action: 'index')
            } else {
                flash.error = linkResource.errors
                render flash.error
            }
        } else {
            render "resource not created"
        }
    }

}
