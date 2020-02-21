package com.myretail.products.services.web;

import org.springframework.http.ResponseEntity;

import com.myretail.products.services.exception.ServiceException;

import reactor.core.publisher.Mono;

public interface WebClientSevice {

    Mono<ResponseEntity<String>> getResponse(String baseUrl, String resourcePath) throws ServiceException;

}
