package com.myretail.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.myretail.products.domain.model.Price;
import com.myretail.products.domain.model.PriceInformation;
import com.myretail.products.domain.model.ProductInformation;

/*
 * aggregator service for the product
 */
@Service
public class DefaultProductService implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProductService.class);
    
    @Autowired
    private ProductInformationService productInformationService;
    
    @Autowired
    private ProductPriceService productPriceService;
    
    @Override
    public ProductInformation getProductInformation(String productId) {
        Assert.notNull(productId, "productId can't be null");
        LOGGER.info("get product information for {}", productId);
        /*
         * expecting no exception
         * ignore handling exception here
         * however might need to be revisited based on the future requirement
         */
        String productName = productInformationService.getProductName(productId);
        Price price = productPriceService.getPrice(productId);
        return ProductInformation.builder()
                .id(productId)
                .current_price(price)
                .name(productName)
                .build();
    }

    @Override
    public PriceInformation updatePriceInformation(String productId, Price price) {
        Assert.notNull(productId, "productId can't be null");
        Assert.notNull(price, "price can't be null");
        LOGGER.info("udpating priceinformation {} for {}", price, productId);
        return productPriceService.updatePriceInformation(productId, price);
    }
}
