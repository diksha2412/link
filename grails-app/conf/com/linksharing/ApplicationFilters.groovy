package com.linksharing

class ApplicationFilters {

    def filters = {
        sessionCheck(controller:'*', action:'*') {
            before = {

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
//        loginCheck(controller: 'login', invert:true){
//            if(!session.user){
//                redirect(controller: 'login', action: 'index')
//            }
//        }
    }
}
