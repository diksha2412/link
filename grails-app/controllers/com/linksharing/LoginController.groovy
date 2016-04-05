package com.linksharing

import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.User
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.WebAttributes

import javax.servlet.http.HttpServletResponse

@Secured(['permitAll'])
class LoginController {

    /**
     * Dependency injection for the authenticationTrustResolver.
     */
    def authenticationTrustResolver

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    /**
     * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
     */
    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        } else {
            redirect action: 'auth', params: params
        }
    }

    /**
     * Show the login page.
     */
    def auth() {

        def config = SpringSecurityUtils.securityConfig

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }

        String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        List<Resource> resources = Resource.showTopPosts()
        List<Resource> recentShares = Resource.showRecentShares()
        render view: 'home', model: [postUrl            : postUrl,
                                     rememberMeParameter: config.rememberMe.parameter,
                                     resources          : resources,
                                     recentShares       : recentShares]
    }

    /**
     * The redirect action for Ajax requests.
     */
    def authAjax() {
        response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
        response.sendError HttpServletResponse.SC_UNAUTHORIZED
    }

    /**
     * Show denied page.
     */
    def denied() {
        if (springSecurityService.isLoggedIn() &&
                authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication)) {
            // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
            redirect action: 'full', params: params
        }
    }

    /**
     * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
     */
    def full() {
        def config = SpringSecurityUtils.securityConfig
        render view: 'auth', params: params,
                model: [hasCookie: authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication),
                        postUrl  : "${request.contextPath}${config.apf.filterProcessesUrl}"]
    }

    /**
     * Callback after a failed login. Redirects to the auth page with a warning message.
     */
    def authfail() {

        String msg = ''
        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        if (exception) {
            if (exception instanceof AccountExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.expired")
            } else if (exception instanceof CredentialsExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.passwordExpired")
            } else if (exception instanceof DisabledException) {
                msg = g.message(code: "springSecurity.errors.login.disabled")
            } else if (exception instanceof LockedException) {
                msg = g.message(code: "springSecurity.errors.login.locked")
            } else {
                msg = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        if (springSecurityService.isAjax(request)) {
            render([error: msg] as JSON)
        } else {
            flash.message = msg
            redirect action: 'auth', params: params
        }
    }

    /**
     * The Ajax success redirect url.
     */
    def ajaxSuccess() {
        render([success: true, username: springSecurityService.authentication.name] as JSON)
    }

    /**
     * The Ajax denied redirect url.
     */
    def ajaxDenied() {
        render([error: 'access denied'] as JSON)
    }

    /*def index() {
        if (session.userId) {
            redirect(controller: 'User', action: 'index')
        } else {
            List<Resource> resources = Resource.showTopPosts()
            List<Resource> recentShares = Resource.showRecentShares()
            render view: 'home', model: [resources: resources, recentShares: recentShares]
        }
    }*/

    /*def login(String userName, String password) {
        Map jsonResponseMap = [:]
        User user = User.findByUserNameAndPassword(userName, password)
        if (user) {
            if (user.active) {
                session.userId = user.id
                jsonResponseMap.message = "Login successful.(JSON) "
                flash.message = "Login successful."
                redirect(controller: 'user', action: 'index')
            } else {
                jsonResponseMap.error="Your account is not active(JSON)"
                flash.error = "Your account is not active"
            }
        } else {
            jsonResponseMap.error="Either user name or password is incorrect(JSON)"
            flash.error = "Either user name or password is incorrect"
            redirect(action: 'index')
        }
        JSON jsonResponse = jsonResponseMap as JSON
        render(jsonResponse)
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')
    }*/

    def validateEmail() {
        Boolean result = User.findByEmail(params.email) ? false : true
        render result
    }

    def validateUserName() {
        Boolean result = User.findByUserName(params.userName) ? false : true
        render result
    }
}
