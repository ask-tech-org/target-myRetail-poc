package com.myretail.oauth2authorization.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@Builder
public class ErrorInformation {
    
    private LocalDateTime time_stamp;
    private String error_code;
    private String error_message;
    //placeholder error property in case error has associated document 
    private String error_document;
}
