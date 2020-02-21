package com.myretail.products.services.exception;

public abstract class ServiceException extends RuntimeException{
    
    private static final long serialVersionUID = 3694898744200638671L;
    
    public ServiceException() { }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
