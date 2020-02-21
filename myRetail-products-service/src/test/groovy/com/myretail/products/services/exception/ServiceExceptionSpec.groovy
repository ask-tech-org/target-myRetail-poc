package com.myretail.products.services.exception

import static com.myretail.products.testdata.TestData.*

import com.myretail.products.services.exception.ServiceException

import spock.lang.Specification

class ServiceExceptionSpec extends Specification {
    
    def "test_constructor_success"() {
        given:
        def exception1 = new ServiceException() { }
        def exception2 = new ServiceException(MESSAGE) { }
        def exception3 = new ServiceException(MESSAGE, CAUSE) { }
        
        expect:
        exception1.message == null
        exception1.cause == null
        
        exception2.message == MESSAGE
        exception2.cause == null
        
        exception3.message == MESSAGE
        exception3.cause == CAUSE
    }
}
