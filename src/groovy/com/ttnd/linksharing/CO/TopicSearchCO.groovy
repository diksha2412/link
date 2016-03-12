package com.ttnd.linksharing.CO

import com.enums.Visibility
import com.ttnd.linksharing.User

class TopicSearchCO {
    Long id                 //user id
    Visibility visibility

    User getUser(){
        User.get(id)
    }
}
