package com.ttnd.linksharing

import com.ttnd.linksharing.co.UserSearchCO
import com.ttnd.linksharing.vo.UserVO

class User {

    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    Boolean admin = false
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

    static namedQueries = {
        search { UserSearchCO userSearchCO ->

            if (userSearchCO.q) {

                or
                        {
                            ilike('firstName', "%${userSearchCO.q}%")
                            ilike('lastName', "%${userSearchCO.q}%")
                            ilike('emailID', "%${userSearchCO.q}%")
                            ilike('userName', "%${userSearchCO.q}%")

                        }
            }

            if (userSearchCO.active != null) {
                eq('active', userSearchCO.active)
            }

            eq('admin', false)
        }

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

    Subscription getSubscription(Long topicId) {
        Subscription.findByUserAndTopic(this, Topic.get(topicId))
    }

    Boolean canDeleteResource(Resource resource) {
        resource.createdBy.equals(this) || this.admin
    }

    Boolean isSubscribed(Long topicId) {
        Subscription.findByUserAndTopic(this, Topic.read(topicId))
    }

    List<Resource> unreadResources() {
        return ReadingItem.createCriteria().list {
            projections {
                property('resource')
            }
            eq('user', this)
            eq('isRead', false)
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
