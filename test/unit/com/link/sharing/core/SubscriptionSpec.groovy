package com.link.sharing.core

import com.enums.Seriousness
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def "validating subscription"(){
        given:"a subscription"
        Subscription subscription= new Subscription(topic: topic, user: user, seriousness: seriousness)

        when:"validate is invoked"
        Boolean valid = subscription.validate()

        then:
        valid==result

        where:
        sno | topic       | user       | seriousness        | result
        1   | new Topic() | new User() | Seriousness.CASUAL | true
        2   | null        | new User() | Seriousness.CASUAL | false
        3   | new Topic() | null       | Seriousness.CASUAL | false
        4   | new Topic() | new User() | null               | false

        }

    def "validating duplicate subscription"(){
        given:"2 subscriptions"
        User user=new User()
        Topic topic=new Topic()
        Subscription subscription1=new Subscription(topic: topic, user: user, seriousness: Seriousness.CASUAL)
        Subscription subscription2=new Subscription(topic: topic, user: user, seriousness: Seriousness.CASUAL)

        when:
        subscription1.save(flush: true)
        subscription2.save(flush: true)

        then:
        !subscription1.errors.allErrors.size()
        subscription2.errors.allErrors.size()
        subscription2.errors.getFieldError('user')
    }


    }

