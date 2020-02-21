package com.myretail.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.myretail.products.services.utilities.JsonService;
import com.myretail.products.services.web.WebClientSevice;

import reactor.core.publisher.Mono;

@Service
public class DefaultProductInformationService implements ProductInformationService {
    
    private static final String PRODUCT_NAME_FIELD_NAME = "title";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProductInformationService.class);
    
    @Value("${myretail.productinformation.api.baseurl:https://redsky.target.com}")
    private String baseUrl;

    @Value("${myretail.productinformation.api.resourcePath:/v2/pdp/tcin/}")
    private String resourcePath;
        
    @Autowired
    private JsonService<String> jsonService;
    
    @Autowired
    private WebClientSevice webClientService;
    
    @Override
    public String getProductName(String productId) {
        Assert.notNull(productId, "productId can't be null");
        String productName = "";
        try {
            Mono<ResponseEntity<String>> response = webClientService
                    .getResponse(baseUrl, new StringBuilder()
                            .append(resourcePath)
                            .append(productId).toString());
            response.subscribe();
            String responseBody = response.block().getBody();
            
            //only parsing title from response so that we are not dependent on the full data contract
            productName = jsonService.getFieldValue(responseBody, PRODUCT_NAME_FIELD_NAME);
            LOGGER.info("retrieved productName {}", productName);
        } catch (Exception e) {
            //ignore throwing any exception assuming user will satisfy with overall partial product information.
            //however it all depends on the business requirement
            //if needed it should throw externalserviceexception
            String message = "error retrieving product name";
            LOGGER.error(message, e);
        }
        return productName;
    }
}
