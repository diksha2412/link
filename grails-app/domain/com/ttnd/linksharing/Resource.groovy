package com.ttnd.linksharing

abstract class Resource {

    String description
    User createdBy
    RatingInfoVO ratingInfoVO
    Date dateCreated
    Date lastUpdated

    static hasMany = [resourceratings: ResourceRating, readingItems:ReadingItem]
    static belongsTo = [topic:Topic ]
    static transients = ['ratingInfoVO']

    static mapping = {
        description( type : 'text')
    }

    static namedQueries = {
        search { ResourceSearchCO resourceSearchCO ->
            eq ('topic.id', resourceSearchCO.topicId)
        }

        resourceSearch { ResourceSearchCO resourceSearchCO ->
            eq ('topic.visibility', resourceSearchCO.visibility)

        }
    }

    RatingInfoVO getRatingInfo(){
        List result=ResourceRating.createCriteria().get {
            projections {
                count('id','totalVotes')
                sum('score','totalScore')
                avg('score','avgScore')
            }
            eq('user',this)
            order('totalVotes','desc')
        }
        new  RatingInfoVO(totalVotes: result[0], totalScore: result[1], avgScore: result[2])
    }


}
