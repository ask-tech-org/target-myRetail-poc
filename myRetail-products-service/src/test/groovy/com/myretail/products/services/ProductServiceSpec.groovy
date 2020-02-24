package com.myretail.products.services

import static com.myretail.products.testdata.TestData.*
import com.myretail.products.exception.ProductNotFoundException
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    private ProductInformationService mockProductInformationService = Mock()
    private ProductPriceService mockProductPriceService = Mock()

    private DefaultProductService mockDefaultProductService = new DefaultProductService( 
        productInformationService: mockProductInformationService,
        productPriceService: mockProductPriceService)

    def "test_getProductInformation_success"() {
        when:
        def result = mockDefaultProductService.getProductInformation(PRODUCT_ID)
        
        then:
        result.name == PRODUCT_NAME
        result.current_price.currency_code == CURRENCY_CODE
        result.current_price.value == VALUE
        
        and:
        1 *  mockProductInformationService.getProductName(PRODUCT_ID) >> PRODUCT_NAME
        1 *  mockProductPriceService.getPrice(PRODUCT_ID) >> PRICE
    }
    
    def "test_getProductInformation_notfound"() {
        when:
        mockDefaultProductService.getProductInformation(PRODUCT_ID)
        
        then:
        def e = thrown(ProductNotFoundException)
        e.message == "product not found"
        
        and:
        1 *  mockProductInformationService.getProductName(PRODUCT_ID) >> null
        1 *  mockProductPriceService.getPrice(PRODUCT_ID) >> null
    }
    
    def "test_getProductInformation_IllegalArgumentException"() {
        when:
        mockDefaultProductService.getProductInformation(null)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == "productId can't be null"
    }
    
    def "test_updatePriceInformation_success"() {
        when:
        def result = mockDefaultProductService.updatePriceInformation(PRODUCT_ID, PRICE)
        
        then:
        result.id == PRODUCT_ID
        result.current_price.currency_code == CURRENCY_CODE
        result.current_price.value == VALUE
        
        and:
        1 *  mockProductPriceService.updatePriceInformation(PRODUCT_ID, PRICE) >> PRICE_INFORMATION
    }
    
    def "test_updatePriceInformation_IllegalArgumentExceptionr"() {
        when:
        mockDefaultProductService.updatePriceInformation(productId, price)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == message
        
        where:
        productId       |    price    |    message
        PRODUCT_ID      |      null   | "price can't be null"
        null            |      PRICE  | "productId can't be null"
        null            |      null   | "productId can't be null"
    }
}
