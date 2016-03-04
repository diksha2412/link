package com.linksharing

import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

class LinkResourceController {

    def index() {}

    def create(String link, String description, Integer topicName) {
        Topic topic = Topic.findById(topicName)
        LinkResource linkResource = new LinkResource(url: link, description: description, createdBy: User.get(session.userId),
                topic: topic)
        if (linkResource) {
            if (linkResource.validate()) {
                linkResource.save()
                topic.addToResources(linkResource)
                flash.message="link resource saved successfully"
                render template: '/user/dashboard'
            } else {
                flash.error = linkResource.errors
                render flash.error
            }
        } else {
            render "resource not created"
        }
    }

}
