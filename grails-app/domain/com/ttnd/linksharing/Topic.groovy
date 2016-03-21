package com.ttnd.linksharing

import com.enums.Seriousness
import com.enums.Visibility
import com.ttnd.linksharing.vo.TopicVO

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

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> topicVoList = []
        String hql = '''
                            SELECT t.id
                            FROM Topic t LEFT JOIN t.resources AS resource
                            GROUP BY t.id
                            ORDER BY COUNT(resource) DESC, name ASC
                            '''
        def ids = Topic.executeQuery(hql, [max: 5])
        def orderedTopics = Topic.getAll(ids)

        orderedTopics.each { Topic topic ->
            topicVoList.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy,
                    count: topic.resources.size()))
        }
        topicVoList
    }

    List<User> getSubscribedUsers() {
        List<User> result = Subscription.createCriteria().list() {
            projections {
                property('user')
            }
            eq('topic.id', this.id)
        }
        result
    }

    Boolean isPublic() {
        this.visibility.equals(Visibility.PUBLIC)
    }

    Boolean canBeViewedBy(User user) {
        (isPublic() || user.subscribedTopics.contains(this) || user.admin) ? true : false
    }

    List<Topic> search(String search) {
        Topic.createCriteria().list {
            createAlias('createdBy', 'user')
            or {
                like('name', search)
                like('user.userName', search)
            }
        }
    }
}