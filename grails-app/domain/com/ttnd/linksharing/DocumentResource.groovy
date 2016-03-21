package com.ttnd.linksharing

class DocumentResource extends Resource {

    String filePath
    Date dateCreated
    Date lastUpdated
    String contentType
    String fileName

    static transients = ['contentType', 'fileName']

    static constraints = {
        filePath(blank: false)
        contentType bindable: true, matches: Constants.DOCUMENT_CONTENT_TYPE
    }

    String toString() {
        filePath
    }

    String getContentType() {
        return contentType
    }

    void setContentType(String contentType) {
        this.contentType = contentType
    }
}
