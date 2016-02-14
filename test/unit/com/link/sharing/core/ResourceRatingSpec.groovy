package com.link.sharing.core

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    def "validating resource rating"(){
        given:"a resource rating"
        ResourceRating resourceRating=new ResourceRating(resource: resource, user: user, score: score)

        when:
        Boolean valid=resourceRating.validate()

        then:
        valid==result

        where:
        sno | resource           | user       | score | result
        1   | new LinkResource() | new User() | 3     | true
        2   | null               | new User() | 3     | false
        3   | new LinkResource() | null       | 3     | false
        4   | new LinkResource() | new User() | 10    | false
        5   | new LinkResource() | new User() | 0     | false
    }

    def "cheking for duplicate resource rating "(){
        given:
        LinkResource resource=new LinkResource()
        User user=new User()
        ResourceRating resourceRating1=new ResourceRating(resource: resource, user: user, score: 3 )
        ResourceRating resourceRating2=new ResourceRating(resource: resource, user: user, score: 3 )

        when:
        resourceRating1.save(flush: true)
        resourceRating2.save(flush: true)

        then:
        !resourceRating1.errors.allErrors.size()
        resourceRating2.errors.allErrors.size()
        resourceRating2.errors.getFieldError('resource')
    }
    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
