package com.myretail.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.myretail.products.data.model.PriceDocument;
import com.myretail.products.domain.model.Price;
import com.myretail.products.domain.model.PriceInformation;
import com.myretail.products.repositories.PriceDocumentRepositories;
import com.myretail.products.services.exception.InternalServiceException;

@Service
public class DefaultProductPriceService implements ProductPriceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProductPriceService.class);
    
    @Autowired
    private PriceDocumentRepositories  priceDocumentRepositories;
    
    @Override
    public Price getPrice(String productId) {
        Assert.notNull(productId, "productId can't be null");
        Price price = null;
        try {
            PriceDocument priceDocument = priceDocumentRepositories.findByProductId(productId);
            price = Price.builder()
                    .currency_code(priceDocument.getCurrency_code())
                    .value(priceDocument.getValue())
                    .build();
            LOGGER.info("retrieved price information {}", price);
        } catch (Exception e) {
            /*
             * similar to ProductInformationService, ignore throwing any exception 
             * assuming user will satisfy with overall partial product information.
             * however it all depends on the business requirement
             * if needed it should throw internalserviceexception
             */
            String message = "error retrieving price information";
            LOGGER.error(message, e);
        }
        return price;
    }

    @Override
    public PriceInformation updatePriceInformation(String productId, Price price) {
        Assert.notNull(productId, "productId can't be null");
        Assert.notNull(price, "price can't be null");
        try {
            PriceDocument priceDocument = PriceDocument.builder()
                    .productId(productId)
                    .currency_code(price.getCurrency_code())
                    .value(price.getValue())
                    .build();
            
            //according to spring data, save works as both update or create
            //if it finds a matching data by the id, it just update the existing record. 
            PriceDocument savePriceDocument = priceDocumentRepositories.save(priceDocument);
            LOGGER.info("udpated priceinformation for {}", savePriceDocument);
            
            return PriceInformation.builder()
                    .current_price(Price.builder()
                            .currency_code(savePriceDocument.getCurrency_code())
                            .value(savePriceDocument.getValue())
                            .build())
                    .id(savePriceDocument.getProductId())
                    .build();
        } catch(Exception e) {
            String message = "error updating price information";
            LOGGER.error(message, e);
            throw new InternalServiceException(message, e);
        }
    }

}
