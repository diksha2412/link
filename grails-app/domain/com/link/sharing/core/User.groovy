package com.link.sharing.core

class User {

    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated

    static transients = ['fullName']

    static hasMany = [topics:Topic, subscriptions:Subscription, readingItems: ReadingItem, resources: Resource ]

    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        password(nullable: false, blank: false, minSize: 5)
        firstName(nullable: false, blank: false)
        lastName(blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
    }

    static mapping = {
        photo(sqlType:'longblob')
    }
    String getFullName() {
        [firstName, lastName].findAll { it }.join(' ')
    }


}
