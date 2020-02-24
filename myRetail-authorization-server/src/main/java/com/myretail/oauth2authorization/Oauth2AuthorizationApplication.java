package com.myretail.oauth2authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
/*
 * ignoring utility class constructor error 
 * since this is part of the spring application design
 */
public class Oauth2AuthorizationApplication {
    /*
     * implement this non reactive oauth2.0 authorization server, just to facilitate authentication and authorization support for oauth2.0 client credential grant types
     * just implemented a bare minimun functionalities (ClientCredentials - userInfoUri) for POC based on the following:
     * #https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html/boot-features-security-oauth2-resource-server.html
     * however, it can be extended to support JWT token and all other oauth2.0 grant types e.g. Authorization Code, Refresh Token 
     * TODO need to revisit based on the requirement 
     */
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthorizationApplication.class, args);
    }
    
}
