package com.myretail.products.configurations.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myretail.products.utilities.UuidUtility;

//according spring framework, OncePerRequestFilter aims to guarantee a single execution per request dispatch, on any servlet container
//appending correlationId to the log for request tracing purpose.
//if X-Correlation-Id found in request header, appending that Id to the log, Otherwise generating a new correlation-id 
//recommonded pattern: ask clients to always send X-Correlation-Id to the header.
//otherwise it will be hard to debug without Correlation-Id
@Configuration
public class Slf4jMDCFilter extends OncePerRequestFilter {
    
    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    private static final String CORRELATION_ID_LOG_NAME = "correlationId";
    
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
        throws java.io.IOException, ServletException {
        try {
            final String token;
            if (!StringUtils.isEmpty(CORRELATION_ID_HEADER_NAME) && !StringUtils.isEmpty(request.getHeader(CORRELATION_ID_HEADER_NAME))) {
                token = request.getHeader(CORRELATION_ID_HEADER_NAME);
            } else {
                token = UuidUtility.generateUniqueCorrelationId();
            }
            MDC.put(CORRELATION_ID_LOG_NAME, token);
            if (!StringUtils.isEmpty(CORRELATION_ID_LOG_NAME)) {
                response.addHeader(CORRELATION_ID_LOG_NAME, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID_LOG_NAME);
        }
    }
}
