package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([Topic, User, ReadingItem])
@TestFor(UserController)
class UserControllerSpec extends Specification {

    def "test index"() {
        setup:"a user"
        User user=User.get(session.userId)

        when:"calling index action"
        controller.index()

        then:"view and models should be rendered"
        model.subscribedTopics == user.subscribedTopics
        model.trendingTopics == Topic.getTrendingTopics()
        model.reasingItems == ReadingItem.getReadingItems(user)
        model.subscriptions == user.subscriptions
        model.user == user
        view == "/user/dashboard"
    }
}
