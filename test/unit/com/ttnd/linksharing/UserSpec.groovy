package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    @Unroll("#sno")
    def "validating user"(){
        setup: "A user"
        User user = new User(email: email, firstName: fname, lastName: lname, password: pwd,
                userName: userName, admin: admin,active: active, photo: photo)

        when: "when the method is called"
        Boolean result = user.validate()

        then: "check for the result"
        output == result

        where:
        sno | email           | fname    | lname   | pwd      | userName | admin | active | photo         | output
        1   | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | true  | true   | "photo".bytes | true
        2   | null            | "diksha" | "ahuja" | "test12" | "uname"  | true  | true   | "photo".bytes | false
        3   | "abc@gmail.com" | null     | "ahuja" | "test12" | "uname"  | true  | true   | "photo".bytes | false
        4   | "abc@gmail.com" | "diksha" | null    | "test12" | "uname"  | true  | true   | "photo".bytes | false
        5   | "abc@gmail.com" | "diksha" | "ahuja" | null     | "uname"  | true  | true   | "photo".bytes | false
        6   | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | null     | true  | true   | "photo".bytes | false
        7   | ""              | "diksha" | "ahuja" | "test12" | "uname"  | true  | true   | "photo".bytes | false
        8   | "abc@gmail.com" | ""       | "ahuja" | "test12" | "uname"  | true  | true   | "photo".bytes | false
        9   | "abc@gmail.com" | "diksha" | ""      | "test12" | "uname"  | true  | true   | "photo".bytes | false
        10  | "abc@gmail.com" | "diksha" | "ahuja" | ""       | "uname"  | true  | true   | "photo".bytes | false
        11  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | ""       | true  | true   | "photo".bytes | false
        12  | "123"           | "diksha" | "ahuja" | "test12" | "uname"  | true  | true   | "photo".bytes | false
        13  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | true  | true   | null          | true
        14  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | null  | true   | "photo".bytes | true
        15  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | true  | null   | "photo".bytes | true
        16  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | false | true   | "photo".bytes | true
        17  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | true  | false  | "photo".bytes | true
        18  | "abc@gmail.com" | "diksha" | "ahuja" | "test12" | "uname"  | true  | true   | "".bytes      | true




    }

    def "get full name"() {
        given: "a user"
        User user = new User()

        and: "first name"
        user.firstName = "diksha"

        and: "full name"
        user.lastName = "ahuja"

        when: "get full name() is called"
        user.getFullName()

        then:
        user.firstName + " " + user.lastName == "diksha ahuja"
    }

    def "test for unique email"(){
        setup:"A user"
        User user1=new User(email: "abc2@gmail.com", password: "test123", userName: "diksha2412", firstName: "diksha",
                lastName: "ahuja", photo: "photo1".bytes, admin: true, active: false)
        User user2=new User(email: "abc2@gmail.com", password: "test456", userName: "diksha24", firstName: "pulkit",
                lastName: "ahuja", photo: "photo1".bytes, admin: false, active: false)

        when:"the constraint for unique is checked"
        user1.save(flush: true)
        user2.save(flush: true)

        then:"check for the result"
        !user1.errors.allErrors.size()
        user2.errors.allErrors.size()
        user2.errors.getFieldError('email')
    }

    def "testing toString()"(){
        given: "a user"
        User user = new User(firstName: firstName)

        when:"toString is called"
        String result = user.toString()

        then:
        result==output

        where:
        firstName | output
        "diksha"  | "diksha"
    }

}
