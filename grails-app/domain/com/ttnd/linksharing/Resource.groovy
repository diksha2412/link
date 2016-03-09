package com.ttnd.linksharing

import com.enums.Visibility
import org.hibernate.criterion.CriteriaSpecification
import com.ttnd.linksharing.ResourceRating

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

    Boolean isLinkResource(){

        println "--->> ${this}"
       this.instanceOf(LinkResource) ? true : false
    }

    Boolean canBeViewedBy(User user){
        this.topic.canBeViewedBy(user)
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
