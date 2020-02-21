package com.myretail.products.services;

import com.myretail.products.domain.model.Price;
import com.myretail.products.domain.model.PriceInformation;
import com.myretail.products.domain.model.ProductInformation;

public interface ProductService {
    
    ProductInformation getProductInformation(String productId);

    PriceInformation updatePriceInformation(String productId, Price price);
}
