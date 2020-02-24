package com.myretail.products.exception

import static com.myretail.products.testdata.TestData.*

import org.springframework.http.HttpStatus

import spock.lang.Specification

class RestExceptionSpec extends Specification {
    
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND
    
    def "test_constructor_success"() {
        given:
        def exception1 = new RestException(STATUS) { }
        def exception2 = new RestException(STATUS, MESSAGE) { }
        def exception3 = new RestException(STATUS, MESSAGE, CAUSE) { }
        
        expect:
        exception1.status == STATUS
        exception1.message == null
        exception1.cause == null
        
        exception2.status == STATUS
        exception2.message == MESSAGE
        exception2.cause == null
        
        exception3.status == STATUS
        exception3.message == MESSAGE
        exception3.cause == CAUSE
    }
}
