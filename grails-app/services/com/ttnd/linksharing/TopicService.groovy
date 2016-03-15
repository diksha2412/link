package com.ttnd.linksharing

import com.ttnd.linksharing.co.TopicSearchCO
import grails.transaction.Transactional

@Transactional
class TopicService {

    List<Topic> search(TopicSearchCO topicSearchCO) {
        Topic.findAllByCreatedByAndVisibility(topicSearchCO.getUser(), topicSearchCO.visibility)
    }
}
