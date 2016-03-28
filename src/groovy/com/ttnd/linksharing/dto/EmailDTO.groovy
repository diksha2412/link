package com.ttnd.linksharing.dto

class EmailDTO {
    String to
    String subject
    String view
    String content
    Map model

    static constraints = {
        model(nullable: true)
        content(nullable: true)
        view(nullable: true)

        subject(nullable: false)
        to(nullable: false)

    }
}
