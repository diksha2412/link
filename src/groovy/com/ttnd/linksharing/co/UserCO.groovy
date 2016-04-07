package com.ttnd.linksharing.co

import com.ttnd.linksharing.User
import grails.validation.Validateable

@Validateable
class UserCO {
    String email
    String username
    String password
    String firstName
    String lastName
    String confirmPassword
    Byte[] photo

    static constraints = {
        importFrom(User)
        confirmPassword bindable: true, nullable: true, validator: { val, obj ->
            if (val && obj.password) {
                val.equals(obj.password) ? true : 'com.linksharing.user.password.dont.match'
            }
        }
    }
}
