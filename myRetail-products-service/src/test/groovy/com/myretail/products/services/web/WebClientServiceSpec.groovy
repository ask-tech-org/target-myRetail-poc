package com.myretail.products.services.web

import static com.myretail.products.testdata.TestData.*
import spock.lang.Specification

class WebClientServiceSpec extends Specification {

    private DefaultWebClientSevice defaultWebClientSevice = new DefaultWebClientSevice()
    
    def "test_getResponse_success"() {
        expect:
        defaultWebClientSevice.getResponse(BASE_URL, RESOURCE_PATH) != null
    }

}
