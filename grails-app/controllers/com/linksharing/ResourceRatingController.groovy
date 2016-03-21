package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.User

class ResourceRatingController {

    def index() {}

    def save(int score, Long resourceId) {
        User user = User.get(session.userId)
        Resource resource = Resource.get(resourceId)
        if (resource) {
            ResourceRating resourceRating = ResourceRating.findByResourceAndUser(resource, user)
            resourceRating.score = score
            resourceRating.save(flush: true)
            flash.success = "resource rating saved successfully"
            redirect(controller: 'user', action: 'index')
        } else {
            flash.error = "resource not found"
        }
    }
}
