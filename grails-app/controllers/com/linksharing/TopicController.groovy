package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceSearchCO
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

class TopicController {

    def index() {

    }

    def show(Long topicId) {
        //Topic topic = Topic.get(id)
        Topic topic=Topic.read(topicId)
        if (!topic) {
            render ${topic.properties}
           // flash.error = "no topic in database"
//            redirect(controller: 'user', action: 'index')
            } else if (topic.visibility == Visibility.PUBLIC) {
//            render view: '/topic/show'
            render "${topic.properties}"
        } else {
            User user1 = User.findByUserName(session.userId)
            if (Subscription.findByTopicAndUser(topic, user1)){
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


    def save(String topic, String visibility) {
        Topic topic1=new Topic(name: topic, createdBy: User.get(session.userId), visibility: Visibility.convert(visibility))

        if (topic1.validate()) {
            topic1.save()
            flash.message = 'topic saved successfully'
            render template: '/user/dashboard'
        }else {
            log.error('error in saving the topic')
            flash.error = topic1.errors
            render(flash.error)
        }
    }
}
