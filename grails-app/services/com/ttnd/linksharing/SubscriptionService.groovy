package com.ttnd.linksharing

import com.ttnd.linksharing.co.TopicSearchCO
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    List<Topic> search(TopicSearchCO topicSearchCO) {
        User user=topicSearchCO.getUser()
        List<Topic> topics=user.getSubscribedTopics()
        return topics
    }
}
