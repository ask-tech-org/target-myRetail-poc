package com.myretail.oauth2authorization.controllers

import java.security.Principal
import javax.management.remote.JMXPrincipal
import spock.lang.Specification

class OauthResourceControllerSpec extends Specification {

    private OauthResourceController oauthResourceController = new OauthResourceController()
    private Principal principal = new JMXPrincipal("test")
    
    def "test_user_sueccess"() {
        when:
        def result = oauthResourceController.user(principal)
        
        then:
        result.name == "test"
    }

    def "test_getProductInformation_IllegalArgumentException"() {
        when:
        oauthResourceController.user(null)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == "user can't be null"
    }
    
}
