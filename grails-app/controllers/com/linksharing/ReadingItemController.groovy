package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User
import grails.converters.JSON

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId, Boolean isRead) {
        Map jsonResponseMap = [:]
        ReadingItem readingItem = ReadingItem.findByIdAndUser(resourceId, User.get(session.userId))
        if (readingItem) {
            readingItem.isRead = !isRead
            readingItem.save(flush: true, failOnError: true)
            jsonResponseMap.message = "updated successfully"
        } else {
            jsonResponseMap.error ="error in updation"
        }
        JSON jsonResponse=jsonResponseMap as JSON
        render jsonResponse
    }
}
