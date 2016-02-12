package com.link.sharing.core

import com.enums.Seriousness


class Subscription {

    Topic topic
    User user
    Date lastUpdated
    Date dateCreated
    Seriousness seriousness

    static belongsTo = [user: User, topic:Topic]

    static constraints = {
        user (nullable: false)
        topic (nullable: false, unique: 'user')
        seriousness (nullable: false)

    }
}
