package com.myretail.oauth2authorization.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
@RequestMapping(value = "/api/v1")
public class OauthResourceController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OauthResourceController.class);
    
    //check if the user has a valid token to access the resource
    //based on the bearer token provided by the client application
    //principal name get retrieved from the authorization server 
    //based on the bearer token and client credentials grant_type
    //during the access token request
    @RequestMapping("/authorize/user")
    public Principal user(Principal user) {
        Assert.notNull(user, "user can't be null");
        LOGGER.info("authorize user {}", user.getName());
        return user;
    }
}
