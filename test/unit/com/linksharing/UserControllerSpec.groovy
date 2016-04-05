package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.vo.TopicVO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([Topic, User, ReadingItem])
@TestFor(UserController)
class UserControllerSpec extends Specification {

    def "test index"() {

        given:"a user"
        User user=new User()
        controller.session.userId=user.id

        and:"trending topics(list of topic VOs)"
        Topic.metaClass.static.getTrendingTopics ={->
            [new TopicVO()]
        }

        and:"a readingItemsList"
        ReadingItem.metaClass.static.getReadingItems={User u->
            [new ReadingItem()]
        }

        and:"subscribedTopics"
        List<Topic> subscribedTopics=user.getSubscribedTopics()

        and: "subscriptions"
        List<Subscription> subscriptions=user.subscriptions


        when:"calling index action"
        controller.index()

        then:"view and models should be rendered"
        view == "/user/dashboard"
        model.subscribedTopics.size()==1
        model.trendingTopics.size()==1
        model.readingItemsList.size()==1
    }
}
