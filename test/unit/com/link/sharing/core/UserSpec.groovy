package com.link.sharing.core

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    @Unroll
    def "test cases for validating strings"(){
        setup: "A user"
        User user = new User(email: email, firstName: fname, lastName: lname, password: pwd)

        when: "when the method is called"
        Boolean result = user.validate()

        then: "check for the result"
        output == result

        where:
        email           | fname    | lname   | pwd      | output
        ""              | "diksha" | "ahuja" | "test12" | false
        "abc@gmail.com" | ""       | "ahuja" | "test12" | false
        "abc2gmail.com" | "diksha" | ""      | "test12" | false
        "abc@gmail.com" | "diksha" | "ahuja" | ""       | false
        "abc@gmail.com" | "diksha" | "ahuja" | null     | false
        null            | "diksha" | "ahuja" | "test12" | false
        "abc@gmail.com" | "diksha" | "ahuja" | "test12" | false
        "abc@gmail.com" | "diksha" | "ahuja" | "test12" | true 
        "abc@gmail.com" | "diksha" | "ahuja" | "test"   | false

    }

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
