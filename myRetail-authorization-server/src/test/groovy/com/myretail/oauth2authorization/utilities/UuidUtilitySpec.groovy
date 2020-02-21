package com.myretail.oauth2authorization.utilities

import com.myretail.oauth2authorization.utilities.UuidUtility

import spock.lang.Specification

class UuidUtilitySpec extends Specification {
 
    def "test_generateUniqueCorrelationId_sucess"() {
        expect:
        UuidUtility.generateUniqueCorrelationId() != null
    }
}
