package com.myretail.products.configurations.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterRegistrationConfiguration {
    
    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> servletRegistrationBean() {
        final FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<OncePerRequestFilter>();
        final Slf4jMDCFilter log4jMDCFilterFilter = new Slf4jMDCFilter();
        registrationBean.setFilter(log4jMDCFilterFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
