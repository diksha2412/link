package com.ttnd.linksharing.dto

class EmailDTO {
    List<String> to
    String subject
    String view
    String content
    Map model

    static constraints = {
        model(nullable: true)
        to(nullable: false)
        view(nullable: true)
        subject(nullable: false)
        content(nullable: true)
    }
}
