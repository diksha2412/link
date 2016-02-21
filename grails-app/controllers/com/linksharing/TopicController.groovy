package com.linksharing

import com.enums.Visibility
import com.link.sharing.core.Topic

class TopicController {

    def index() {

    }
    def show(int id){
        Topic topic=Topic.get(id)
        if(!topic){
            redirect(controller: 'login', action: 'index')
            flash.error ="no topic in database"
        } else if(topic.visibility==Visibility.PUBLIC){
            render "success"
        } else {

        }
    }
}
