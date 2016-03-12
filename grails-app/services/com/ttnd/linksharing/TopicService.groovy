package com.ttnd.linksharing

import com.ttnd.linksharing.CO.TopicSearchCO
import grails.transaction.Transactional

@Transactional
class TopicService {

    List<Topic> search(TopicSearchCO topicSearchCO) {
        Topic.findAllByCreatedByAndVisibility(topicSearchCO.getUser(), topicSearchCO.visibility)
    }
}
