package com.myretail.products.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.myretail.products.domain.model.Price;
import com.myretail.products.domain.model.PriceInformation;
import com.myretail.products.domain.model.ProductInformation;
import com.myretail.products.services.ProductService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Mono<ProductInformation> getProductionInformation(@PathVariable("id") String productId) {
        Assert.notNull(productId, "productId can't be null");
        LOGGER.info("##getting production information for {}", productId);
        return Mono.just(productService.getProductInformation(productId));
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public Mono<PriceInformation> updateProductionInformation(@PathVariable("id") String productId, @RequestBody Price price) {
        Assert.notNull(productId, "productId can't be null");
        Assert.notNull(price, "price can't be null");
        LOGGER.info("##updating production information for {}", price);
        return Mono.just(productService.updatePriceInformation(productId, price));
    }
}
