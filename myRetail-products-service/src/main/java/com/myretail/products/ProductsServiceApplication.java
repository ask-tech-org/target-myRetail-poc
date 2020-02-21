package com.myretail.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myretail.products.ProductsServiceApplication;

@SpringBootApplication
public class ProductsServiceApplication {
    
    //https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
    //keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass password
    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
        //https://gist.github.com/anthavio/98885c6155c7ec991ec9
    }
    
}
