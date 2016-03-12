package com.linksharing

import com.ttnd.linksharing.Constants
import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

class DocumentResourceController extends ResourceController {

    def index() {}

    @Transactional
    def save(DocumentResource documentResource) {
        
        println "=======inside doc res save==========="

        documentResource.createdBy = User.get(session.userId)

        MultipartFile file = params.file as MultipartFile

        if (file.empty) {
            println "=======file empty=========="
            flash.error = "Invalid file"
        } else {
            String filePath = "${grailsApplication.config.grails.linksharing.folderPath}/${UUID.randomUUID()}.pdf"
            documentResource.contentType = file.contentType
            documentResource.filePath = filePath

            if (documentResource.save(flush: true)) {
                println "===========resource saved=============="
                File savedFile = new File(filePath)
                params.file.transferTo(savedFile)
                println "==========1============="
                addToReadingItems(documentResource)

                flash.message = "document saved successfully"
            } else {
                flash.error = "error in saving file"
            }
        }
        redirect(controller: 'user', action: 'index')
    }

    def download(Long id) {
        println "==========inside doc res download=============="
        User user = User.get(session.userId)
        DocumentResource documentResource = (DocumentResource) Resource.get(id)

        if (documentResource && documentResource.canBeViewedBy(user)) {
            println "resource exists and can be viewed"
            def file = new File(documentResource.filePath)

            if (file.exists()) {
                println "===file exists======"
                response.setContentType(Constants.DOCUMENT_CONTENT_TYPE)
                response.setHeader("Content-disposition", "attachment,fileName=${documentResource.fileName}")
                response.outputStream << file.bytes
            }
            flash.error = "Desired resource doesn't exist"
        } else {
            flash.error = "Cannot access the desired resource. Permission denied."
        }
//        redirect(controller: 'resource', action: 'show', params: [id: id])
    }
}
