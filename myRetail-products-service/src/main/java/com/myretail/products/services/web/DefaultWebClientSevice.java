package com.myretail.products.services.web;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.myretail.products.services.exception.ServiceException;
import reactor.core.publisher.Mono;

@Service
public class DefaultWebClientSevice implements WebClientSevice {

    @Override
    public Mono<ResponseEntity<String>> getResponse(String baseUrl, String resourcePath) throws ServiceException {
        return WebClient.create(baseUrl)
                .method(HttpMethod.GET)
                .uri(resourcePath)
                .exchange()
                .flatMap(transformer -> transformer.toEntity(String.class));
    }

}
