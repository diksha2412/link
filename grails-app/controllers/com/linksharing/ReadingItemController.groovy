package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId, Boolean isRead ) {
        println "====inside changeIsRead========="
        println "====resource id is : ${resourceId}"
        println "====isRead is : ${isRead}"
        
        Resource resource = Resource.get(resourceId)
        
        User user=User.get(session.userId)

        if (ReadingItem.executeUpdate("update ReadingItem as r set r.isRead=:isRead where r.resource=:resource and " +
                "r.user = :user", [isRead: isRead, user: user, resource: resource])) {
            render "Reading Item isRead successfully changed. ~SUCCESS~"
        } else {
            render "Reading Item isRead could not be changed. ~FAILURE~"
        }
    }

//    def changeIsRead(Long Id, Boolean isRead) {
//        Resource resource = Resource.get(id)
//        User user = session.user
//        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id and user.id=: userId", [isRead: isRead, userId: user.id])) {
//            render "Success."
//        } else {
//            render "Error."
//        }
//    }
}
