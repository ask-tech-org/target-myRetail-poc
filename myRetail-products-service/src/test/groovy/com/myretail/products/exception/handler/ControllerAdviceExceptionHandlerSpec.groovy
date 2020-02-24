package com.myretail.products.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.request.ServletWebRequest
import com.myretail.products.exception.ProductNotFoundException
import spock.lang.Specification

class ControllerAdviceExceptionHandlerSpec extends Specification {

    private MockHttpServletRequest  mockHttpServletRequest = new MockHttpServletRequest()
    private MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse()
    private ServletWebRequest servletWebRequest = new ServletWebRequest(mockHttpServletRequest, mockHttpServletResponse)
    
    private ControllerAdviceExceptionHandler controllerAdviceExceptionHandler = new ControllerAdviceExceptionHandler()
    
    def "test_handleProductNotFoundException_sucess"() {
        given:
        def exception = new ProductNotFoundException("ProductNotFoundException")
        
        when:
        def result = controllerAdviceExceptionHandler.handleNotFoundServiceException(exception)

        then:
        result.status == HttpStatus.NOT_FOUND
        result.body.error_code == HttpStatus.NOT_FOUND.toString()
        result.body.error_message == exception.message
        result.body.error_document == null
    }
    
    def "test_handleIllegalArgumentException_sucess"() {
        given:
        def exception = new IllegalArgumentException("IllegalArgumentException")
        
        when:
        def result = controllerAdviceExceptionHandler.handleIllegalArgumentException(exception)

        then:
        result.status == HttpStatus.BAD_REQUEST
        result.body.error_code == HttpStatus.BAD_REQUEST.toString()
        result.body.error_message == exception.message
        result.body.error_document == null
    }
    
    def "test_handleGenericException_sucess"() {
        given:
        def exception = new RuntimeException("RuntimeException")
        
        when:
        def result = controllerAdviceExceptionHandler.handleGenericException(exception, servletWebRequest)

        then:
        result.status == HttpStatus.INTERNAL_SERVER_ERROR
        result.body.error_code == HttpStatus.INTERNAL_SERVER_ERROR.toString()
        result.body.error_message == exception.message
        result.body.error_document == null
    }
}
