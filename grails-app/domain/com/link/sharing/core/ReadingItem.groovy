package com.link.sharing.core

class ReadingItem {

    Resource resource
    User user
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User]

    static constraints = {
        resource(unique: 'user', nullable: false)
        isRead(nullable: false)
        user(nullable: false)
    }

}
