package com.ttnd.linksharing.co

import grails.validation.Validateable

@Validateable
class UserCO {
    String email
    String userName
    String password
    String firstName
    String lastName
    String confirmPassword
    Byte[] photo
}
