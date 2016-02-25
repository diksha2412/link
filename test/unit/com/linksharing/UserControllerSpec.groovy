package com.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {

    void "test index"() {
        when:"calling index action"
        controller.index()

        then:"content should be rendered"
        response.contentAsString=="user dashboard"
    }
}
