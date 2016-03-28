package com.linksharing

import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import grails.transaction.Transactional

class LinkResourceController extends ResourceController {

    def index() {}

    @Transactional
    def create(String link, String description, Integer topicName) {
        Topic topic = Topic.findById(topicName)
        if (topic) {
            Resource resource = new LinkResource(url: link, description: description, createdBy: User.get(session.userId),
                    topic: topic)

            if (resource) {
                if (resource.validate()) {
                    resource.save(flush: true)
                    topic.addToResources(resource)
                    addToReadingItems(resource)
                    flash.message = "link resource saved successfully"
                    redirect(controller: 'user', action: 'index')
                } else {
                    flash.error = resource.errors
                    render flash.error
                }
            } else {
                render "resource not created"
            }
        }
    }
}
