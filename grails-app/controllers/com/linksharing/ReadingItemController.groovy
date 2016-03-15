package com.linksharing

import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User
import grails.converters.JSON

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId, Boolean isRead) {
        println "========inside changeIsRead========="
        println "======resource id is : ${resourceId}"
        println "======isRead is: ${isRead}"
        Map jsonResponseMap = [:]
        ReadingItem readingItem = ReadingItem.findByIdAndUser(resourceId, User.get(session.userId))
        if (readingItem) {
            println "=====reading item found"
            readingItem.isRead = isRead
            readingItem.save(flush: true, failOnError: true)
            println("=======changed isRead is : ${readingItem.isRead}")
            println "=========changed========"
            jsonResponseMap.message = "updated successfully"
        } else {
            jsonResponseMap.error ="error in updation"
        }
        JSON jsonResponse=jsonResponseMap as JSON
        render jsonResponse
    }
}
