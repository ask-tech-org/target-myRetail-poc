package com.myretail.products.exception.handler;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.myretail.products.domain.model.ErrorInformation;

//handling exception in a single class
//because, as per POC, we don't have resource specific exception 
@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorInformation> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.badRequest()
                .body(ErrorInformation.builder()
                        .time_stamp(LocalDateTime.now())
                        .error_code(HttpStatus.BAD_REQUEST.toString())
                        .error_message(illegalArgumentException.getMessage())
                        .build());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInformation> handleGenericException(Exception exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorInformation.builder()
                        .time_stamp(LocalDateTime.now())
                        .error_code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .error_message(exception.getMessage())
                        .build());
    }
}
