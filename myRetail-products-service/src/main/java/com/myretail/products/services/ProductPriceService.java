package com.myretail.products.services;

import com.myretail.products.domain.model.Price;
import com.myretail.products.domain.model.PriceInformation;

public interface ProductPriceService {

    Price getPrice(String productId);
    
    PriceInformation updatePriceInformation(String productId, Price price);
}
