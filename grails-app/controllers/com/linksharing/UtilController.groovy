package com.linksharing

class UtilController {
    def grailsApplication
    def index() {
        render("hello !!")
        render(grailsApplication.config.grails.testValue)
        log.info "Sample info"
        log.error "logging error"
    }
}
