package com.myretail.products.configurations.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OauthResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.authorization.request.matcher:/api/**}")
    private String authorizationRequestMatcher;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(authorizationRequestMatcher)
            .authenticated();
    }
}
