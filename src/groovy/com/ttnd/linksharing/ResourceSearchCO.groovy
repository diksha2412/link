package com.ttnd.linksharing

import com.enums.Visibility
import grails.validation.Validateable

@Validateable
class ResourceSearchCO extends SearchCO {
    Long topicId
    Visibility visibility
}
