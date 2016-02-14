package com.link.sharing.core

import com.enums.Seriousness


class Subscription {

    Topic topic
    User user
    Date lastUpdated
    Date dateCreated
    Seriousness seriousness

    static belongsTo = [topic:Topic]

    static constraints = {
        user (nullable: false, unique: 'topic')
        topic (nullable: false)
        seriousness (nullable: false)

    }
}
