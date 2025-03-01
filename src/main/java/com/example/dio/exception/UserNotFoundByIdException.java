package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {

    private final String message;
}
