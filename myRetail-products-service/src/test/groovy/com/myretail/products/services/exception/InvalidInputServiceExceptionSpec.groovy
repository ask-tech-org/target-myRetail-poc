package com.myretail.products.services.exception

import static com.myretail.products.testdata.TestData.*
import spock.lang.Specification

class InvalidInputServiceExceptionSpec extends Specification {
    
    def "test_constructor_success"() {
        given:
        def exception1 = new InvalidInputServiceException() { }
        def exception2 = new InvalidInputServiceException(MESSAGE) { }
        def exception3 = new InvalidInputServiceException(MESSAGE, CAUSE) { }
        
        expect:
        exception1.message == null
        exception1.cause == null
        
        exception2.message == MESSAGE
        exception2.cause == null
        
        exception3.message == MESSAGE
        exception3.cause == CAUSE
    }
}
