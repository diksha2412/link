package com.ttnd.linksharing

class ResourceRating {

    Resource resource
    User user
    Integer score
    Date dateCreated
    Date lastUpdated

    static belongsTo = [resource: Resource]

    static constraints = {
        score(min: 1, max: 5, nullable: false)
        resource(unique: 'user')
        user nullable: false
    }

    void getRating() {
        List<Resource> list = ResourceRating.createCriteria().list() {
            projections {
                sum('score', 'votes')
                groupProperty('resource_id')
            }
            order('votes', 'desc')
        }
    }

    static List<Resource> topPosts() {
        List<Resource> resources = []
        def result = ResourceRating.createCriteria().list(max: 5) {
            projections {
                property('resource.id')
                //property('resource.description')
            }
            groupProperty('resource.id')
            count('id', 'totalVotes')
            order('totalVotes', 'desc')
        }
        List list = result.collect { it[0] }
        resources = Resource.getAll(list)

        resources
    }
}
