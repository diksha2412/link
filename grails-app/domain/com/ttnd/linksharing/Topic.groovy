package com.ttnd.linksharing

import com.enums.Seriousness
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

    static mapping = {
        sort name: 'asc'
    }

    String toString(){
        name
    }

    def afterInsert(){
        Topic.withNewSession {
            Subscription subscription=new Subscription(topic: this, user: this.createdBy, seriousness: Seriousness.VERY_SERIOUS)
            if(subscription.save()){
                log.info "subscription saved successfully"
            } else {
                log.error("error in saving subscription")
            }
        }
    }

    static List<TopicVO> getTrendingTopics(){
        List<TopicVO> result = Topic.createCriteria().list() {
            projections {
                createAlias("Topic", "t")
                groupProperty("t.id")
                property("t.name")
                sum("balance", 'totalBalance')
            }
            order("totalBalance", "desc")
            order("b.name", "desc")
        }
        render "Result -> ${result}"
    }
}
