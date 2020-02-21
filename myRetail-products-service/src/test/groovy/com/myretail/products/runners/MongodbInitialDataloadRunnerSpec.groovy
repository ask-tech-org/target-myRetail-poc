package com.myretail.products.runners

import com.myretail.products.repositories.PriceDocumentRepositories
import com.myretail.products.data.model.PriceDocument
import com.myretail.products.services.utilities.JsonService
import spock.lang.Specification

class MongodbInitialDataloadRunnerSpec extends Specification {
    
    //things to remember: this spock test is on junit4
    private static final String MOCK_INTITAL_LOAD_FILE = "classpath:mongodb-initial-load.json"
    private PriceDocumentRepositories mockPriceDocumentRepositories = Mock()
    private JsonService<PriceDocument> mockJsonService = Mock()
    private List<PriceDocument> priceDocuments = []
    
    private MongodbInitialDataloadRunner mockMongodbInitialDataloadRunner = new MongodbInitialDataloadRunner(
        priceDocumentRepositories: mockPriceDocumentRepositories,
        jsonService: mockJsonService,
        initialLoadFile: MOCK_INTITAL_LOAD_FILE
        )
    
    def "test_run_success"() {
        given:
        1 * mockJsonService.convertToObjects(_, PriceDocument) >> priceDocuments
        1 * mockPriceDocumentRepositories.saveAll(priceDocuments) >> priceDocuments
        
        expect:
        mockMongodbInitialDataloadRunner.run()

    }

}
