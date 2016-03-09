package com.ttnd.linksharing

import com.enums.Seriousness
import com.enums.Visibility
import org.hibernate.criterion.CriteriaSpecification


class Topic {

    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static hasMany = [resources: Resource, subscriptions: Subscription]

    static transients = ['subscribedUsers']

    static constraints = {
        name(nullable: false, blank: false, unique: 'createdBy')
        createdBy(nullable: false)
        visibility(nullable: false)
    }

    static mapping = {
        sort name: 'asc'
    }

    String toString() {
        name
    }

    def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(topic: this, user: this.createdBy, seriousness: Seriousness.VERY_SERIOUS)
            if (subscription.save()) {
                log.info "subscription saved successfully"
            } else {
                log.error("error in saving subscription")
            }
        }
    }

//    static List<TopicVO> getTrendingTopics() {
//        List<TopicVO> trendingTopics = Resource.createCriteria().list([max: 5]) {
//            createAlias('topic', 't')
//            projections {
//                groupProperty('t.id')
//                Property('t.name')
//                Property('t.visibility')
//                count('r.id', 'resourceCount')
//            }
//            order('resourceCount', 'desc')
//            order('name')
//        }
//        trendingTopics
//    }
//}

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> topicVoList = []
        String hql = '''
                            SELECT t.id
                            FROM Topic t LEFT JOIN t.resources AS resource
                            GROUP BY t.id
                            ORDER BY COUNT(resource) DESC, name ASC
                            '''
        def ids = Topic.executeQuery(hql,[max:5])
        def orderedTopics = Topic.getAll(ids)

        println "${orderedTopics}"
        orderedTopics.each { Topic topic ->
            topicVoList.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy,
                    count: topic.resources.size()))
        }
        topicVoList
    }

    static List<User> getSubscribedUsers(){
        List<User> result= Subscription.createCriteria().list(){
            projections{
                property('user')
            }
            eq('topic.id',this.id)
        }
        result
    }

    Boolean isPublic(){
        this.visibility.equals(Visibility.PUBLIC)
    }

    Boolean canBeViewedBy(User user){
        (isPublic() || user.subscribedTopics.contains(this) || user.admin) ? true : false
    }
}