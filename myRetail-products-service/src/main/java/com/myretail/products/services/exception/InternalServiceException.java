package com.myretail.products.services.exception;

public class InternalServiceException extends  ServiceException {

    private static final long serialVersionUID = 181481116033952565L;

    public InternalServiceException() { }
    
    public InternalServiceException(String message) {
        super(message);
    }
    
    public InternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
