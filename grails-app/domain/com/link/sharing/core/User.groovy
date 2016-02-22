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

  static transients = ['fullName', 'confirmPassword']

    static hasMany = [topics:Topic, subscriptions:Subscription, readingItems: ReadingItem, resources: Resource ]

    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        password(nullable: false, blank: false, minSize: 5)
        firstName(nullable: false, blank: false)
        lastName(blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
//        confirmPassword(blank: true, nullable: true, validator: { val, obj ->
//            if (!password.equals(confirmPassword)) {
//                return false
//            }
//        })
    }

    static mapping = {
        photo(sqlType:'longblob')
    }

    String getFullName() {
        [firstName, lastName].findAll { it }.join(' ')
    }

    String toString(){
        firstName
    }

}
