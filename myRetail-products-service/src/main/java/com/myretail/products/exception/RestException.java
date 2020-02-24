package com.myretail.products.exception;

import org.springframework.http.HttpStatus;

public abstract class RestException extends RuntimeException {

    private static final long serialVersionUID = 8372730925090114463L;

    private final HttpStatus status;
    
    public RestException(HttpStatus status) {
        super();
        this.status = status;
    }
    
    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    
    public RestException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
}
