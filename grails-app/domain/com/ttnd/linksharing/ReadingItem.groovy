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

    static List<ReadingItem> getReadingItems(User user) {
        List<ReadingItem> readingItemList = ReadingItem.createCriteria().list() {
            eq('isRead', false)
            eq('user', user)
        }
        readingItemList
    }
}
