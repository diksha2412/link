package com.linksharing

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

        println "=====inside addToReadingItems of resource controller========"

        Topic topic = resource.topic

        List<User> subscribedUsers = topic.getSubscribedUsers()

        subscribedUsers.each { User user ->
            println "====${user} subscribed user=========="
            if (resource.createdBy != user) {
                user.addToResources(resource)
                println "======resource added=========="
                user.addToReadingItems(new ReadingItem(resource: resource, user: user).save(failOnError: true, flush: true))
                println "========reading item added=========="
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

    def searchString(String queryString) {
        List<Resource> list1 = Resource.findAllByDescriptionIlike("%${queryString}%")
        render view: '/resource/search', model: ['resources': list1, 'queryString': queryString]
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
