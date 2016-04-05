class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')
        "/"(controller: 'user', action: 'index')
//        "500"(view: '/custom500')
//        "404"(view: '/custom404')
	}
}
