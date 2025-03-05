package com.example.dio.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {
    /**
     * Helps creating the success response with data including the http status code, message
     * and data itself.
     * @param status the status of the operations
     * @param  message the message to the user
     * @param  data the dat involved in the operation
     * @return responseEntity of type ResponseStructure or type T (the involved in the operation
     */
    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data) {
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .body(structure);
    }

    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String message) {
        SimpleErrorResponse error = SimpleErrorResponse.builder()
                .type(status.name())
                .message(message)
                .status(status.value())
                .build();

        return ResponseEntity.status(status).body(error);

    }
    public static ResponseEntity<FieldErrorResponse> response(HttpStatus status, String message, List<FieldErrorResponse.FieldError> errors){
        FieldErrorResponse error = FieldErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .message(message)
                .errors(errors)
                .build();
        return ResponseEntity.status(status)
                .body(error);
    }

    public static <T> ResponseEntity<ResponseStructure<T>> created(String message, T data) {
        return success(HttpStatus.CREATED, message, data);
    }

    public static <T> ResponseEntity<ResponseStructure<T>> ok(String message, T data) {
        return success(HttpStatus.OK, message, data);
    }

    public static <T> ResponseEntity<SimpleErrorResponse> notFound(String message) {
        return error(HttpStatus.NOT_FOUND, message);
    }

}