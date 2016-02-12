package com.link.sharing.core

abstract class Resource {

    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static hasMany = [resourceratings: ResourceRating, readingItems:ReadingItem]
    static belongsTo = [topic:Topic ]

    static constraints = {
        description( type : 'text')
    }


}
