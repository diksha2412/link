package com.ttnd.linksharing

class ReadingItem {

    Resource resource
    User user
    Boolean isRead = false
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        resource(nullable: false, unique: 'user')
        isRead(nullable: false)
        user(nullable: false)
    }

    static Integer changeIsRead(Long id, Boolean isRead){
        //ReadingItem readingItem=ReadingItem.get(id)
        Integer result=ReadingItem.executeUpdate("update ReadingItem as r set r.isRead =: isRead " +
                "where r.id=:id", [isRead: true, id: id])
        result
    }
}
