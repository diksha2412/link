package com.link.sharing.core

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    @Unroll("#sno")
    def "validating link resource"(){
        given:
        LinkResource linkResource=new LinkResource(url: url, description: description, createdBy: createdBy, topic: topic)

        when:
        Boolean valid=linkResource.validate()

        then:
        valid==result

        where:
        sno | url                      | description | createdBy  | topic       | result
        1   | null                     | "abc"       | new User() | new Topic() | false
        2   | "www.google.com"         | null        | new User() | new Topic() | false
        3   | "www.google.com"         | "abc"       | null       | new Topic() | false
        4   | "www.google.com"         | "abc"       | new User() | null        | false
        5   | "1234667"                | "abc"       | new User() | new Topic() | false
        6   | "http://www.google.com/" | "abc"       | new User() | new Topic() | true

    }

    def "checking toString"(){
        given :" a link resource"
        LinkResource linkResource=new LinkResource(url: url)

        when:
        String result= linkResource.toString()

        then:
        result==output

        where:
        url|output
        "www.google.com"|"www.google.com"

    }
    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
