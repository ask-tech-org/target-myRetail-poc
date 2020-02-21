package com.myretail.products.services.exception

import static com.myretail.products.testdata.TestData.*
import spock.lang.Specification

class InternalServiceExceptionSpec extends Specification {
    
    def "test_constructor_success"() {
        given:
        def exception1 = new InternalServiceException() { }
        def exception2 = new InternalServiceException(MESSAGE) { }
        def exception3 = new InternalServiceException(MESSAGE, CAUSE) { }
        
        expect:
        exception1.message == null
        exception1.cause == null
        
        exception2.message == MESSAGE
        exception2.cause == null
        
        exception3.message == MESSAGE
        exception3.cause == CAUSE
    }
}
