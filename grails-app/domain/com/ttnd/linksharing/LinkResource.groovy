package com.ttnd.linksharing

class LinkResource extends Resource{

    String url
    Date dateCreated
    Date lastUpdated

    static constraints = {
        url(url: true)
    }

    String toString(){
        url
    }
}
