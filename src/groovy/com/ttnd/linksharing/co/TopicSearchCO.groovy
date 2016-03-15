package com.ttnd.linksharing.co

import com.enums.Visibility
import com.ttnd.linksharing.User

class TopicSearchCO {
    Long id                 //user id
    Visibility visibility

    User getUser(){
        User.get(id)
    }
}
