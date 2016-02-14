package com.link.sharing.core

class ReadingItem {

    Resource resource
    User user
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User, resource:Resource]

    static constraints = {
        resource( nullable: false, unique: 'user')
        isRead(nullable: false)
        user(nullable: false)
    }

}
