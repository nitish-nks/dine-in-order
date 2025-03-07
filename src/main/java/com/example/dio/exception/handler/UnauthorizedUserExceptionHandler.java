package com.example.dio.exception.handler;

import com.example.dio.exception.UnauthorizedUserException;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnauthorizedUserExceptionHandler {

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<SimpleErrorResponse> handleUnauthorizedUserException(UnauthorizedUserException ex) {
        return ResponseBuilder.error(HttpStatus.FORBIDDEN, ex.getMessage());
    }
}
