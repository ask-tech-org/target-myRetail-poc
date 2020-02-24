package com.myretail.oauth2authorization.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OauthAuthenticationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /*
     * helpful articles:
     * https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide#authenticationmanager-bean
     * https://developer.okta.com/blog/2018/04/02/client-creds-with-spring-boot
     */

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.oauth2.clientcredentials.resourceid:api}")
    private String resourceId;
    
    @Value("${security.oauth2.clientcredentials.clientid:test}")
    private String clientId;

    @Value("${security.oauth2.clientcredentials.clientsecret:testsecret}")
    private String clientSecret;

    //setting default to 1 hours
    @Value("${security.oauth2.clientcredentials.accesstoken.validitity:3600}") 
    private int accessTokenValiditySeconds;
    
    //setting default to client_credentials just for POC
    //however can be configured for any Oauth2.0 spec grant types
    @Value("${security.oauth2.authorizedgranttypes:client_credentials}")
    private String[] authorizedGrantTypes;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        
        // For the sake of POC project and demonstration purpose using inMemory ClientDetailsServiceConfigurer
        // Ideally in real production environment, we should use external data source for managing clients information
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .authorizedGrantTypes(authorizedGrantTypes)
                .scopes("read", "write") //just making sure changes to the scopes are intentional, require code compilation
                .resourceIds(resourceId);
    }
}
