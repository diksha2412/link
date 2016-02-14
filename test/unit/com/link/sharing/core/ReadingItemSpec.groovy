package com.link.sharing.core

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    def "checking uniqueness constraint"(){
        given:
        LinkResource resource=new LinkResource()
        User user=new User()
        ReadingItem readingItem1=new ReadingItem(resource: resource, user: user, isRead: true)
        ReadingItem readingItem2=new ReadingItem(resource: resource, user: user, isRead: true)

        when:
        readingItem1.save(flush: true)
        readingItem2.save(flush: true)

        then:
        !readingItem1.errors.allErrors.size()
        readingItem2.errors.allErrors.size()
        readingItem2.errors.getFieldError('resource')
    }
    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
