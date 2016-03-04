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
        Resource resource=Resource.load(params.id)
        if(resource){
            try {
                resource.delete()
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

    def show(Long id){
        Resource resource=Resource.get(id)
        if (resource){
            RatingInfoVO ratingInfoVO=resource.getRatingInfo()
            render "${ratingInfoVO}"
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
