package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    def "validating document resource"(){
        given:
        DocumentResource documentResource=new DocumentResource(filePath: filePath, description: description,
                createdBy: createdBy, topic: topic)
        when:
        Boolean valid=documentResource.validate()

        then:
        valid==result

        where:
        sno | filePath       | description | createdBy  | topic       | result
        1   | "/home/diksha" | "abc"       | new User() | new Topic() | true
        2   | null           | "abc"       | new User() | new Topic() | false
        3   | "/home/diksha" | null        | new User() | new Topic() | false
        4   | "/home/diksha" | "abc"       | null       | new Topic() | false
        5   | "/home/diksha" | "abc"       | new User() | null        | false


    }

    def "testing toString"(){
        given:"a document resource"
        DocumentResource documentResource=new DocumentResource(filePath: filePath)

        when:"toString is invoked"
        String result=documentResource.toString()

        then:
        result==output

        where:
        filePath|output
        "some/file/path"|"some/file/path"


    }
    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
