package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User
import grails.transaction.Transactional

@Transactional
class UserService {
    def emailService

    /*def demoMethod(int a,int b) {
        int sum=a+b
    }*/

    def sendUnreadItemsEmail() {
        getUserWithUnreadItems().each { user ->
            emailService.sendUnreadResourcesEmail(user, getUnreadResources(user))
        }
    }

    List<User> getUserWithUnreadItems() {
        return Resource.usersWithUnreadResources()
    }

    List<Resource> getUnreadResources(User user) {
        return user.unreadResources()
    }
}
