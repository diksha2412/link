package com.linksharing

import com.ttnd.linksharing.User

class DemoTagLib {
    static defaultEncodeAs = [taglib:'html']

    static namespace = "ls"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showAdmin= { attrs, body ->
        Boolean result=Boolean.valueOf(attrs.isAdmin)
        if(result){
            out << body()
        }
    }

    def showUserList={
        List<User> users=User.list()
        out << render(template: '/usersList', model: [users: users])
    }
}
