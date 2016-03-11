package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.Resource

import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.VO.TopicVO
import com.ttnd.linksharing.User

class ResourceController {

    def index() {}

    private addToReadingItems(Resource resource){

        println "=====inside addToReadingItems of resource controller========"

        Topic topic=resource.topic

        List<User> subscribedUsers = topic.getSubscribedUsers()

        subscribedUsers.each { User user ->
            if (resource.createdBy!=user){
                user.addToResources(resource)
            }
        }
    }

    def deletion() {
        println params
        Resource resource = Resource.load(params.resourceId)
        if (resource) {
            println "resource found"
            try {
                resource.delete(flush: true)
                println "resource deleted successfully"
                flash.message = "resource deleted successfully"
                redirect(controller: 'user', action: 'index')
            } catch (Exception e) {
                render("object couldn't be deleted ")
            }
        }
    }

    def search(ResourceSearchCO resourceSearchCO) {
        if (resourceSearchCO.q) {
            resourceSearchCO.visibility = Visibility.PUBLIC
        }
    }

    def show(Long resourceId) {
        Resource resource = Resource.get(resourceId)
        User user = User.get(session.userId)
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        if (resource && resource.canBeViewedBy(user)) {
            render(view: 'show', model: [trendingTopics: trendingTopics, resource: resource])
//            RatingInfoVO ratingInfoVO=resource.getRatingInfo()
//            render "${ratingInfoVO}"
        } else {
            render 'resource could not be found'
        }
    }

    def showTopics() {
        List<TopicVO> topicVOs = Topic.getTrendingTopics()
        render topicVOs
    }

}
