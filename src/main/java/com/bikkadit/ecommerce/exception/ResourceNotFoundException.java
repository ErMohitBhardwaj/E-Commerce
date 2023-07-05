package com.bikkadit.ecommerce.exception;

import com.bikkadit.ecommerce.dto.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor

public class ResourceNotFoundException extends RuntimeException{
private String message;
private String resource;
private String fieldValue;

    public ResourceNotFoundException(String message,String resource, String fieldValue) {
      super(String.format("%s with %s : %s ",message,resource,fieldValue));
       this.message=message;
       this.resource=resource;
        this.fieldValue = fieldValue;
    }
}
