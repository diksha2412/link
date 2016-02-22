package com.link.sharing.core

class DocumentResource extends Resource {

    String filePath
    Date dateCreated
    Date lastUpdated

    static constraints = {
        filePath( blank: false)
    }

    String toString(){
        filePath
    }
}
