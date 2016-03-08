package com.linksharing

import com.enums.Visibility
import com.ttnd.linksharing.RatingInfoVO
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceSearchCO
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.TopicVO

class ResourceController {

    def index() {

    }

    def deletion(){
        println  params
        Resource resource=Resource.load(params.resourceId)
        if(resource){
            println "resource found"
            try {
                resource.delete()
                println "resource deleted successfully"
                flash.message="resource deleted successfully"
                redirect(controller: 'user', action: 'index')
            }catch (Exception e) {
                render("object couldn't be deleted ")
            }
        }
    }

    def search(ResourceSearchCO resourceSearchCO){
        if(resourceSearchCO.q){
            resourceSearchCO.visibility=Visibility.PUBLIC
        }
    }

    def show(Long resourceId){
        Resource resource=Resource.get(resourceId)
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        if (resource){
            render(view: 'show', model: [trendingTopics: trendingTopics, resource:resource])
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

    def save(){

    }
}
