package com.link.sharing.core

import com.enums.Visibility


class Topic {

    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static hasMany = [resources: Resource, subscriptions: Subscription]

    static constraints = {
        name (nullable: false, blank: false, unique:'createdBy')
        createdBy (nullable: false)
        visibility (nullable: false)
    }
}
