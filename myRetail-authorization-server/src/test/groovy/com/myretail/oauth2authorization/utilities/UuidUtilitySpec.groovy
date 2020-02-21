package com.myretail.oauth2authorization.utilities

import spock.lang.Specification

class UuidUtilitySpec extends Specification {
 
    def "test_generateUniqueCorrelationId_sucess"() {
        expect:
        UuidUtility.generateUniqueCorrelationId() != null
        String id1 = UuidUtility.generateUniqueCorrelationId()
        assert id1
        assert id1.trim().length() > 10
        String id2 = UuidUtility.generateUniqueCorrelationId()
        String id3 = UuidUtility.generateUniqueCorrelationId()
        assert id1 != id2 && id1 != id3 && id2 != id3
    }
}
