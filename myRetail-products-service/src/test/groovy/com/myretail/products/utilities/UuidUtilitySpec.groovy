package com.myretail.products.utilities

import com.myretail.products.utilities.UuidUtility

import spock.lang.Specification

class UuidUtilitySpec extends Specification {
 
    def "test_generateUniqueCorrelationId_sucess"() {
        expect:
        UuidUtility.generateUniqueCorrelationId() != null
    }
}
