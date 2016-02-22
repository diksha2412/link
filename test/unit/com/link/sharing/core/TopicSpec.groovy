package com.link.sharing.core

import com.enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    @Unroll("#sno")
    def "validating topic"(){
        given:"a topic"
        Topic topic=new Topic(name: name, createdBy: creator, visibility: visibility)

        when:"method is called"
        Boolean res=topic.validate()

        then:
        res==result

        where:
        sno | creator    | name   | visibility         | result
        1   | new User() | "name" | Visibility.PRIVATE | true
        2   | null       | "name" | Visibility.PRIVATE | false
        3   | new User() | ""     | Visibility.PRIVATE | false
        4   | new User() | null   | Visibility.PRIVATE | false
        5   | new User() | "name" | null               | false

    }

    def "validating duplicate topic"(){
        given:
        Topic t1=new Topic(name:"grails", createdBy: new User(), visibility: Visibility.PRIVATE)
        Topic t2=new Topic(name: "grails", createdBy: new User(), visibility: Visibility.PRIVATE)

        when:
        t1.save(flush: true)
        t2.save(flush: true)

        then:
        !t1.errors.allErrors.size()
        t2.errors.allErrors.size()
        t2.errors.getFieldError('name')
    }

    def "checking toString"(){
        given:"a topic"
        Topic topic=new Topic(name: name)

        when:"toString is invoked"
        String result = topic.toString()

        then:
        result==output

        where:
        name|output
        "topic1"|"topic1"
    }

}
