package com.ttnd.linksharing

import grails.validation.Validateable

/**
 * Created by diksha on 3/3/16.
 */

@Validateable
class UserCO {
    String email
    String userName
    String password
    String firstName
    String lastName
    String confirmPassword
}
