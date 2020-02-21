package com.myretail.products.testdata

import com.myretail.products.data.model.PriceDocument
import com.myretail.products.domain.model.Price
import com.myretail.products.domain.model.PriceInformation
import com.myretail.products.domain.model.ProductInformation

class TestData {
    static final String BASE_URL = "baseUrl"
    static final String RESOURCE_PATH = "resourcePath"
    static final String PRODUCT_ID = "productId"
    static final String PRODUCT_RESPONSE_BODY = "{'title': 'test'}"
    static final String PRODUCT_NAME = "productName"
    static final String PRODUCT_NAME_FILED = "title"
    static final String CURRENCY_CODE = "currency_code"
    static final String MESSAGE = "message"
    static final Exception CAUSE = new Exception("cause")
    static final double VALUE = 20.0
    static Price PRICE = new Price(
        value: VALUE,
        currency_code: CURRENCY_CODE
    )
    
    static PriceInformation PRICE_INFORMATION = new PriceInformation(
        id: PRODUCT_ID,
        current_price: PRICE,
    )
  
    static ProductInformation PRODUCT_INFORMATION = new ProductInformation(
          id: PRODUCT_ID,
          name: PRODUCT_NAME,
          current_price: PRICE,
    )
    static PriceDocument PRICE_DOCUMENT = new PriceDocument(
        productId : PRODUCT_ID, 
        currency_code: CURRENCY_CODE,
        value: VALUE,
    )
}
