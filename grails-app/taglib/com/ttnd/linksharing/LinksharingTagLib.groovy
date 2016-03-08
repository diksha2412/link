package com.ttnd.linksharing
import groovy.lang.Closure

class LinksharingTagLib {
//    static defaultEncodeAs = [taglib: 'html']
//    static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def markRead = { attrs, body ->
       User user = User.get(session.userId)
        if (user){
            out << render(template: '/resource/readMark' , model: [readingItem: attrs.readingItem])
        }
    }

    def checkType = { attrs, body ->
        Resource resource=Resource.get(attrs.id as Long)
        if (resource.isLinkResource()){
            out << "<a href='${resource.url}' target='_blank'>View full site</a>"
        } else {
            out << "<a href='${resource.filePath}'>Download</a>"
        }
    }

    def checkDeleteResource = { attrs, body ->
        User user = User.get(session.userId)
        if (user.canDeleteResource(attrs.resource)){
            out<<ls.deleteResource()
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
        out<< render (template:"/login/topPosts", model:[resourceList: resourceList])
    }
}
