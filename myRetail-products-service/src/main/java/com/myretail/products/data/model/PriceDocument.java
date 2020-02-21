package com.myretail.products.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PriceDocument {
    
    //it mostly depends on the business requirement
    //assuming productId is the pricedocument Id
    @Id
    private String productId;
    private double value;
    private String currency_code;
}
