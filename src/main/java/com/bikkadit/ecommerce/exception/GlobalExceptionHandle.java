package com.bikkadit.ecommerce.exception;

import com.bikkadit.ecommerce.helper.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
@ExceptionHandler(ResourceNotFoundException.class)
ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
    ApiResponse response = new ApiResponse();
    response.setMessage(ex.getMessage());
    response.setCode(HttpStatus.NOT_FOUND);
    response.setStatus(false);
    return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
}
}
