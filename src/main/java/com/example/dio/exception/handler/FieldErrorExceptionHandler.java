package com.example.dio.exception.handler;


import com.example.dio.util.FieldErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldErrorResponse.FieldError> errors = new ArrayList<>();
        List<ObjectError> objectErrors = ex.getAllErrors();

        for (ObjectError objectError : objectErrors) {
            FieldError fieldError = (FieldError) objectError;
            FieldErrorResponse.FieldError error = FieldErrorResponse.createFieldError(
                    objectError.getDefaultMessage(),
                    fieldError.getRejectedValue(),
                    fieldError.getField());
            errors.add(error);
        }
        FieldErrorResponse error = FieldErrorResponse.builder()
                .type(status.toString())
                .status(status.value())
                .message("Invalid Input")
                .errors(errors)
                .build();

        return ResponseEntity.status(status)
                .body(errors);

    }
}
