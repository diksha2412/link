package com.ttnd.linksharing

import com.ttnd.linksharing.VO.UserVO
import org.hibernate.sql.Update

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

    static transients = ['fullName', 'confirmPassword', 'subscribedTopics']

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        password(nullable: false, blank: false, minSize: 5)
        firstName(nullable: false, blank: false)
        lastName(blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword bindable: true, nullable: true, validator: { val, obj ->
            val == obj.password ?: 'com.linksharing.user.password.dont.match'
        }
    }


    static mapping = {
        photo(sqlType: 'longblob')
        sort dateCreated: 'asc'
    }

    String getFullName() {
        [firstName, lastName].findAll { it }.join(' ')
    }

    List<Topic> getSubscribedTopics() {
        List<Topic> result = Subscription.createCriteria().list() {
            projections {
                property('topic')
            }
            eq('user.id', this.id)
        }
        result

    }

    Boolean canDeleteResource(Resource resource) {
        resource.createdBy.equals(this) || this.admin
    }

    static Boolean isSubscribed(User user, Long topicId) {
        if (user) {
            List<Subscription> subscriptions = Subscription.createCriteria().list() {
                createAlias('topic', 't')
                projections {
                    property('t.id')
                }
                eq('t.id', topicId)
            }
            if (subscriptions.size() == 0) {
                return false
            } else {
                return true
            }
        }
    }

    UserVO getUserDetails() {
        new UserVO(id: id, email: email, firstName: firstName, lastName: lastName, userName: userName, admin: admin,
                active: active, fullName: fullName)
    }

    String toString() {
        firstName
    }

}
