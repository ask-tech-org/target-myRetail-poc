package com.myretail.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("checkstyle:hideutilityclassconstructor") 
//ignoring utility class constructor error 
//since this is part of the spring application design
public class ProductsServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }
    
}
