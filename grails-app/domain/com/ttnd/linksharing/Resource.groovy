package com.ttnd.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO

abstract class Resource {

    String description
    User createdBy
    RatingInfoVO ratingInfoVO
    Date dateCreated
    Date lastUpdated

    static hasMany = [resourceratings: ResourceRating, readingItems: ReadingItem]
    static belongsTo = [topic: Topic]
    static transients = ['ratingInfoVO']

    static mapping = {
        description(type: 'text')
    }

    static namedQueries = {
        search { ResourceSearchCO resourceSearchCO ->
            eq('topic.id', resourceSearchCO.topicId)
        }

        resourceSearch { ResourceSearchCO resourceSearchCO ->
            eq('topic.visibility', resourceSearchCO.visibility)
        }

        userResourceSearch { ResourceSearchCO resourceSearchCO ->
            eq('createdBy', resourceSearchCO.getUser())
        }
    }

    static List<Resource> showTopPosts() {
        List<Resource> resourceList = []
        def result = ResourceRating.createCriteria().list(max: 5) {
            projections {
                property('resource.id')
            }
            groupProperty('resource.id')
            count('id', 'totalVotes')
            order('totalVotes', 'desc')
        }
        List list = result.collect { it[0] }
        resourceList = Resource.getAll(list)
        resourceList
    }

    static List<Resource> resourceSearch(ResourceSearchCO resourceSearchCO){
        List<Resource> resources=Resource.createCriteria().list(){
            ilike('description', "%${resourceSearchCO.queryString}%")
            topic{
                eq('visibility', Visibility.PUBLIC)
            }
        }
        resources
    }

    Boolean isLinkResource() {
        this.instanceOf(LinkResource)
    }

    Boolean canBeViewedBy(User user) {
        this.topic.canBeViewedBy(user)
    }

    static List<User> usersWithUnreadResources() {
        return ReadingItem.createCriteria().listDistinct {
            projections {
                property('user')
            }
            eq('isRead', false)
        }
    }

//    RatingInfoVO getRatingInfo(){
//        List result=ResourceRating.createCriteria().list() {
//            projections {
//                count('id','totalVotes')
//                sum('score','totalScore')
//                avg('score','avgScore')
//            }
//            eq('resource',this)
//            order('totalVotes','desc')
//        }
//        new  RatingInfoVO(totalVotes: result[0], totalScore: result[1], avgScore: result[2])
//    }
}
