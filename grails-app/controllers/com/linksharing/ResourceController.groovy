package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource

import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.vo.TopicVO
import com.ttnd.linksharing.User

class ResourceController {

    def index() {
    }

    private addToReadingItems(Resource resource) {
        Topic topic = resource.topic
        List<User> subscribedUsers = topic.getSubscribedUsers()
        subscribedUsers.each { User user ->
            if (resource.createdBy != user) {
                user.addToResources(resource)
                user.addToReadingItems(new ReadingItem(resource: resource, user: user).save(failOnError: true, flush: true))
            }
        }
    }

    def deletion() {
        Resource resource = Resource.load(params.resourceId)
        if (resource) {
            try {
                resource.delete(flush: true)
                flash.message = "resource deleted successfully"
                redirect(controller: 'user', action: 'index')
            } catch (Exception e) {
                render("object couldn't be deleted ")
            }
        }
    }

    def searchString(ResourceSearchCO resourceSearchCO) {
        List<Resource> list1 = Resource.resourceSearch(resourceSearchCO)
        render view: '/resource/search', model: ['resources': list1, 'queryString': resourceSearchCO.queryString]
    }

    def search(ResourceSearchCO resourceSearchCO) {
        if (resourceSearchCO.queryString) {
            resourceSearchCO.visibility = Visibility.PUBLIC
        }
    }

    def show(Long resourceId) {
        Resource resource = Resource.get(resourceId)
        User user = User.get(session.userId)
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        if (resource && resource.canBeViewedBy(user)) {
            render(view: 'show', model: [trendingTopics: trendingTopics, resource: resource])
        } else {
            render 'resource could not be found'
        }
    }

    def showEdit() {
        render template: 'edit'
    }

    def edit(String description, Long resourceId) {
        Resource resource = Resource.get(resourceId)
        User user = User.get(session.userId)
        if (user && resource) {
            if (resource.createdBy == user) {
                resource.description = description
                resource.save(flush: true)
            } else {
                flash.message = "existing user doesn't have permission to update"
            }
        } else {
            flash.error = "not found"
        }
    }

    def showTopics() {
        List<TopicVO> topicVOs = Topic.getTrendingTopics()
        render topicVOs
    }
}
