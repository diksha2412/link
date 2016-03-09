package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.User

class ResourceRatingController {

    def index() {}

    def save(int score, Long resourceId) {
        println "===========inside save"
        User user = User.get(session.userId)
        println "=======userId : ${user.id}"
        Resource resource = Resource.get(resourceId)
        println "==========resourceId : ${resource.id}"
        if (resource) {
            println "resource found"

            ResourceRating resourceRating = ResourceRating.findByResourceAndUser(resource, user)
            resourceRating.score = score
            resourceRating.save(flush: true)


            println "score updated"

//            Integer value = ResourceRating.executeUpdate("update ResourceRating r set r.score=:score where " +
//                    "r.resource=:resource and r.user = :user", [score: score, resource: resource, user: user])
//        println "value!!!--${value}"
        } else {
            println "resource not found"
        }
    }
}
