package com.link.sharing.core

class LinkResource extends Resource{

    String url
    Date dateCreated
    Date lastUpdated

    static constraints = {
        url(url: true)
    }
}
