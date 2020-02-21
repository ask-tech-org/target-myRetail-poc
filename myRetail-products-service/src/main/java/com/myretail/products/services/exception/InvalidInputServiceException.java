package com.myretail.products.services.exception;

public class InvalidInputServiceException extends  ServiceException {

    private static final long serialVersionUID = 6333196911681679892L;
    
    public InvalidInputServiceException() { }
    
    public InvalidInputServiceException(String message) {
        super(message);
    }
    
    public InvalidInputServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
