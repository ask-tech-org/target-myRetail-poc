package com.myretail.products.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends  RestException {

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final long serialVersionUID = 2023518922490718482L;

    public ProductNotFoundException() { 
        super(STATUS);
    }
    
    public ProductNotFoundException(String message) {
        super(STATUS, message);
    }
    
    public ProductNotFoundException(String message, Throwable cause) {
        super(STATUS, message, cause);
    }
}
