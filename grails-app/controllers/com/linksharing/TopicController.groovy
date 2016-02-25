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

//    def show(Long id) {
//        Topic topic = Topic.get(id)
//        //Topic topic=Topic.read(params.id)
//        if (!topic) {
//            redirect(controller: 'login', action: 'index')
//            flash.error = "no topic in database"
//        } else if (topic.visibility == Visibility.PUBLIC) {
//            render "success"
//        } else {
//            User user1 = User.findByUserName(session.user)
//            if (Subscription.findByTopicAndUser(topic, user1)){
//                render 'success'
//            } else {
//                redirect(controller: 'user', action: 'index')
//                flash.error = "user subscription doesn't exist for the given topic"
//            }
//        }
//    }

    def show(ResourceSearchCO resourceSearchCO){
        render 'implementing named query to retrieve resources particular to topic id'
        List<Resource> resources=Resource.search(resourceSearchCO).list()
        render resources
    }


    def save(Topic topic, String seriousness) {
        if (!topic.save().hasErrors()) {
            flash.message = 'topic saved without error'
            render 'success'
        } else {
            log.error('error in saving the topic')
            flash.error = ''
            render(topic.save().errors)
        }
    }
}
