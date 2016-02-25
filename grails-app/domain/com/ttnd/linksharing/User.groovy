package com.ttnd.linksharing

class User {

    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    Boolean admin
    Boolean active = true
    Date dateCreated
    Date lastUpdated
    String confirmPassword

    static transients = ['fullName','confirmPassword']

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        password(nullable: false, blank: false, minSize: 5)
        firstName(nullable: false, blank: false)
        lastName(blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword bindable:true, nullable: true, validator: { val, obj ->
            val == obj.password ?: 'com.linksharing.user.password.dont.match'
        }
    }


        static mapping = {
            photo(sqlType: 'longblob')
            sort id: 'desc'
        }

        String getFullName() {
            [firstName, lastName].findAll { it }.join(' ')
        }

        String toString() {
            firstName
        }


}
