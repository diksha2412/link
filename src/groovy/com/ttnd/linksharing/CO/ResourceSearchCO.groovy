package com.ttnd.linksharing.CO

import com.enums.Visibility
import com.ttnd.linksharing.VO.SearchCO
import grails.validation.Validateable

@Validateable
class ResourceSearchCO extends SearchCO {
    Long topicId
    Visibility visibility
}
