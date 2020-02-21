package com.myretail.products.services

import static com.myretail.products.testdata.TestData.*
import org.springframework.http.ResponseEntity
import com.myretail.products.services.utilities.JsonService
import com.myretail.products.services.web.WebClientSevice
import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono
import spock.lang.Specification

class ProductInformationServiceSpec extends Specification {

    private JsonService<String> mcokJsonService  = Mock()
    private WebClientSevice mockWebClientService = Mock()
    private Mono<ResponseEntity<String>> mockMonoRespons = Mock() 
        
    private DefaultProductInformationService mockProductInformationService = new DefaultProductInformationService(
        jsonService: mcokJsonService,
        webClientService: mockWebClientService,
        baseUrl: BASE_URL,
        resourcePath: RESOURCE_PATH
        )
    
    def "test_getProductName_success"() {
        given:
        def responseEntity = new ResponseEntity<>(PRODUCT_RESPONSE_BODY, HttpStatus.OK)

        when:
        def result = mockProductInformationService.getProductName(PRODUCT_ID)
        
        then:
        result == PRODUCT_NAME
        
        and:
        1 *  mockWebClientService.getResponse(BASE_URL, RESOURCE_PATH + PRODUCT_ID) >> mockMonoRespons
        1 *  mockMonoRespons.block() >> responseEntity
        1 *  mcokJsonService.getFieldValue(PRODUCT_RESPONSE_BODY, PRODUCT_NAME_FILED) >> PRODUCT_NAME
    }

    def "test_getProductName_error"() {
        when:
        def result = mockProductInformationService.getProductName(PRODUCT_ID)
        
        then:
        result == ""
        
        and:
        1 *  mockWebClientService.getResponse(BASE_URL, RESOURCE_PATH + PRODUCT_ID) >> mockMonoRespons
        1 *  mockMonoRespons.block()
    }
    
    def "test_getProductName_IllegalArgumentException"() {
        when:
        mockProductInformationService.getProductName(null)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == "productId can't be null"
    }
}
