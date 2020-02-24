package com.myretail.products.repositories

import static com.myretail.products.testdata.TestData.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import com.myretail.products.data.model.PriceDocument
import spock.lang.Specification

@DataMongoTest
class PriceDocumentRepositoriesSpec extends Specification {

    @Autowired
    private PriceDocumentRepositories priceDocumentRepositories

    private document1 = new PriceDocument(productId: PRODUCT_ID, value: VALUE, currency_code: CURRENCY_CODE)
    private document2 = new PriceDocument(productId: "id2", value: 12.0, currency_code: "BDT")
    
    def setup() {
        priceDocumentRepositories.save(document1)
        priceDocumentRepositories.save(document2)
    }

    def "test_findByProductId_success"() {
        when:
        def result = priceDocumentRepositories.findByProductId(PRODUCT_ID)
        
        then:
        result.currency_code == CURRENCY_CODE
        result.productId == PRODUCT_ID
        result.value == VALUE
    }
    
    def "test_findByProductId_error"() {
        expect:
        priceDocumentRepositories.findByProductId(null) == null
    }
}
