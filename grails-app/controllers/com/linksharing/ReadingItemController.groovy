package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId, Boolean isRead ) {
        Resource resource = Resource.get(resourceId)
        Long userId = session.userId

        if (ReadingItem.executeUpdate("update ReadingItem as r set r.isRead=:isRead where r.resource.id=:resourceId and " +
                "r.user.id = :userId", [isRead: isRead, userId: userId, resourceId: resourceId])) {
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
