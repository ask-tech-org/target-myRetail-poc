package com.myretail.products.controllers

import static com.myretail.products.testdata.TestData.*

import com.myretail.products.controllers.ProductController
import com.myretail.products.domain.model.ProductInformation
import com.myretail.products.services.ProductService

import spock.lang.Specification

class ProductControllerSpec extends Specification {

    private ProductService mockProductService = Mock()
    private ProductController mockProductController = new ProductController(
        productService : mockProductService
    );
    
    def "test_getProductionInformation_success"() {
        when:
        def result = mockProductController.getProductionInformation(PRODUCT_ID).block()
        
        then:
        result.current_price == PRICE
        result.name == PRODUCT_NAME
        result.id == PRODUCT_ID
        
        and:
        1* mockProductService.getProductInformation(PRODUCT_ID) >> PRODUCT_INFORMATION 
    }
    
    def "test_getProductionInformation_IllegalArgumentException"() {
        when:
        mockProductController.getProductionInformation(null)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == "productId can't be null"
    }
    
    def "test_updateProductionInformation_success"() {
        when:
        def result = mockProductController.updateProductionInformation(PRODUCT_ID, PRICE).block()
        
        then:
        result.current_price == PRICE
        result.id == PRODUCT_ID
        
        and:
        1* mockProductService.updatePriceInformation(PRODUCT_ID, PRICE) >> PRICE_INFORMATION
    }
    
    def "test_updateProductionInformation_IllegalArgumentExceptionr"() {
        when:
        mockProductController.updateProductionInformation(productId, price)
        
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
