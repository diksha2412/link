package com.ttnd.linksharing

import com.enums.Seriousness
import com.enums.Visibility
import com.ttnd.linksharing.vo.TopicVO

class LinksharingTagLib {
//    static defaultEncodeAs = [taglib: 'html']
//    static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def markRead = { attrs, body ->
        User user = User.get(session.userId)
        if (user) {
            Long resourceId = attrs.resourceId
            Boolean isRead = attrs.isRead
            String link = "${createLink(controller: 'readingItem', action: 'changeIsRead')}"
            if (isRead) {
                out << "<a href=$link class='markReadStatus' resourceId=\"${attrs.resourceId}\" isRead=\"${attrs.isRead}\">Mark as Unread</a>"
            } else {
                out << "<a href=$link class='markReadStatus' resourceId=\"${attrs.resourceId}\" isRead=\"${attrs.isRead}\">Mark as read</a>"
            }
        }
    }

    /* def markRead = { attrs, body ->
         User user = User.get(session.userId)
         if (user) {
             out << render(template: '/resource/readMark', model: [readingItem: attrs.readingItem])
         }
     }*/

    def checkType = { attrs, body ->
        Resource resource = Resource.get(attrs.id as Long)
        if (resource.isLinkResource()) {
            out << "<a href='${resource.url}' target='_blank'>View full site</a>"
        } else {
            String download = "${createLink(controller: 'documentResource', action: 'download', params: [id: attrs.id])}"
            out << "<a href=$download class='download'>Download</a>"
//            out << "<a href='${resource.filePath}'>Download</a>"
        }
    }

    def checkDeleteResource = { attrs, body ->
        User user = User.get(session.userId)
        if (user.canDeleteResource(attrs.resource)) {
            out << ls.deleteResource()
        }
    }

    def deleteResource = { attrs, body ->
        out << render(template: '/resource/delete', model: [resource: attrs.resource])
    }

    def trendingTopics = {
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        out << render(template: '/topic/trendingTopics', model: [trendingTopics: trendingTopics])
    }

    def topPosts = {
        List<Resource> resourceList = Resource.showTopPosts()
        out << render(template: "/login/topPosts", model: [resourceList: resourceList])
    }

    def showSubscribe = { attrs, body ->
        if (session.userId) {
            User user = User.get(session.userId)
            if (!user.isSubscribed(attrs.topicId)) {
                String subscribe = "${createLink(controller: 'subscription', action: 'save')}"
                out << "<a href=$subscribe class='subscriptionSave' topicId=\"${attrs.topicId}\">Subscribe</a>"
            } else {
                String unsubscribe = "${createLink(controller: 'subscription', action: 'delete')}"
                out << "<a href=$unsubscribe class='subscription' topicId=\"${attrs.topicId}\">Unsubscribe</a>"
            }
        }
    }

    def resourceCount = { attrs, body ->
        Topic topic = Topic.read(attrs.topicId)
        out << topic.resources.size()
    }

    def subscriptionCount = { attrs, body ->
        if (attrs.topicId) {
            out << Topic.get(attrs.topicId).subscriptions.size()
        } else {
            out << User.get(session.userId).subscriptions.size()
        }
    }

    def topicCount = { attrs, body ->
        User user = User.get(session.userId)
        out << user.topics.size()
    }

    def userImage = { attrs, body ->
        if (attrs.userId) {
            String src = "${createLink(controller: 'user', action: 'image', params: [userId: attrs.userId])}"
            out << "<img src=${src} class='img-thumbnail'>"
        }
    }

    def canUpdateTopic = { attrs, body ->
        User user=User.get(session.userId)
        Topic topic=Topic.get(attrs.topicId)
        if (user.admin || user==topic.createdBy ){
            out << body()
        } else {
            flash.error="either topic or user is not available"
        }
    }

    def showSeriousness = { attrs,body ->
        User user= User.get(session.userId)
        Topic topic=Topic.get(attrs.topicId)
        if (user){
            Subscription subscription=user.getSubscription(attrs.topicId)
            if (user.getSubscription(attrs.topicId)){
                out<< g.select(class: 'seriousness dropdown-toggle btn btn-default',name:'seriousness', from: Seriousness.values(), value: subscription.seriousness, topicId: attrs.topicId )
            } else {
                flash.error="user not subscribed to topic"
            }
        } else {
            flash.error="user not found"
        }
    }

    def showVisibility = { attrs,body ->
        User user= User.get(session.userId)
        Topic topic=Topic.get(attrs.topicId)
        if (user){
            Subscription subscription=user.getSubscription(attrs.topicId)
            if (user.getSubscription(attrs.topicId)){
                out<< g.select(class: 'visibility dropdown-toggle btn btn-default',name:'visibility', from: Visibility.values(), value: topic.visibility, topicId: attrs.topicId )
            } else {
                flash.error="user not subscribed to topic"
            }
        } else {
            flash.error="user not found"
        }
    }
}
