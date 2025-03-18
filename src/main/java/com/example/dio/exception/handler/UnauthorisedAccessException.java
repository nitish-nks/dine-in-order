package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnauthorisedAccessException extends RuntimeException {
    public UnauthorisedAccessException(String message) {

        super(message);
    }
}
