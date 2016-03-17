package com.ttnd.linksharing

import com.ttnd.linksharing.dto.EmailDTO
import grails.transaction.Transactional

@Transactional
class EmailService {

    def groovyPageRenderer
    def mailService
    def messageSource


    def sendMail(EmailDTO emailDTO) {
        def content
        content = groovyPageRenderer.render(template: "/email/invite", model: [topicId: emailDTO.model.id, hostURL: emailDTO.model.hostURL])

        mailService.sendMail {
            to emailDTO.to
            subject emailDTO.subject
            html content
        }
    }

    def sendUnreadResourcesEmail(User user, List<Resource> unreadResource) {
        EmailDTO emailDTO = new EmailDTO(to: [user.email],
                subject: messageSource.getMessage("com.ttnd.linksharing.dto.EmailDTO.unread.subject", [].toArray(), Locale.default),
                view: "/email/unreadResources", model: [user: user, unreadResource: unreadResource])
        sendMail(emailDTO)
    }
}
