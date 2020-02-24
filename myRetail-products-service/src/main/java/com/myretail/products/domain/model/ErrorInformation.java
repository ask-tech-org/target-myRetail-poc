package com.myretail.products.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInformation {
    
    private LocalDateTime time_stamp;
    private String error_code;
    private String error_message;
    //placeholder error property in case error has associated document 
    private String error_document;
}
