package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.User

class ResourceRatingController {

    def index() {}

    def save(int score){
        println "inside save of rsource rating"

        Resource resource=Resource.get(params.resourceId)
        ResourceRating resourceRating=new ResourceRating(resource: resource, user: User.get(session.UserId), score: score)

        if(resourceRating.validate()){
            println "resource rating validated"
            resourceRating.save()
        } else {
            println "error in saving resource"
            flash.error="error in saving resource"
        }
    }
}
