package com.ttnd.linksharing.co

import com.enums.Visibility
import com.ttnd.linksharing.User
import com.ttnd.linksharing.vo.SearchCO
import grails.validation.Validateable

@Validateable
class ResourceSearchCO extends SearchCO {
    Long topicId
    Visibility visibility
    Long id

    User getUser(){
        User.get(id)
    }
}
