package com.myretail.products.services

import static com.myretail.products.testdata.TestData.*
import com.myretail.products.repositories.PriceDocumentRepositories
import com.myretail.products.services.exception.InternalServiceException
import com.myretail.products.exception.ProductNotFoundException
import spock.lang.Specification

class ProductPriceServiceSpec extends Specification {

    private PriceDocumentRepositories  mockPriceDocumentRepositories = Mock()
        
    private DefaultProductPriceService mockDefaultProductPriceService = new DefaultProductPriceService( priceDocumentRepositories: mockPriceDocumentRepositories )

    def "test_getPrice_success"() {
        when:
        def result = mockDefaultProductPriceService.getPrice(PRODUCT_ID)
        
        then:
        result.currency_code == CURRENCY_CODE
        result.value == VALUE
        
        and:
        1 *  mockPriceDocumentRepositories.findByProductId(PRODUCT_ID) >> PRICE_DOCUMENT
    }
    
    def "test_getPrice_IllegalArgumentException"() {
        when:
        mockDefaultProductPriceService.getPrice(null)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == "productId can't be null"
    }
    
    def "test_getPrice_null"() {
        when:
        def result = mockDefaultProductPriceService.getPrice(PRODUCT_ID)
        
        then:
        result == null
        
        and:
        1 *  mockPriceDocumentRepositories.findByProductId(PRODUCT_ID) >> { throw new Exception() }
    }
    
    def "test_updatePriceInformation_success"() {
        when:
        def result = mockDefaultProductPriceService.updatePriceInformation(PRODUCT_ID, PRICE)
        
        then:
        result.current_price.currency_code == PRICE.currency_code
        result.current_price.value == PRICE.value
        result.id == PRODUCT_ID
        
        and:
        1 *  mockPriceDocumentRepositories.existsById(PRODUCT_ID) >> true
        1 *  mockPriceDocumentRepositories.save(_) >> PRICE_DOCUMENT
    }
    
    def "test_updatePriceInformation_IllegalArgumentException"() {
        when:
        mockDefaultProductPriceService.updatePriceInformation(productId, price)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == message
        
        where:
        productId       |    price    |    message
        PRODUCT_ID      |      null   | "price can't be null"
        null            |      PRICE  | "productId can't be null"
        null            |      null   | "productId can't be null"
    }
    
    def "test_updatePriceInformation_ProductNotFoundException"() {
        when:
        mockDefaultProductPriceService.updatePriceInformation(PRODUCT_ID, PRICE)
        
        then:
        def e = thrown(ProductNotFoundException)
        e.message == "product not found"
        
        and:
        and:
        1 *  mockPriceDocumentRepositories.existsById(PRODUCT_ID) >> false
    }

    def "test_updatePriceInformation_InternalServiceException"() {
        when:
        mockDefaultProductPriceService.updatePriceInformation(PRODUCT_ID, PRICE)
        
        then:
        def e = thrown(InternalServiceException)
        e.message == "error updating price information"
        
        and:
        and:
        1 *  mockPriceDocumentRepositories.existsById(PRODUCT_ID) >> true
        1 *  mockPriceDocumentRepositories.save(_) >> { throw new InternalServiceException() }
    }
}
