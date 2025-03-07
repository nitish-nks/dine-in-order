package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UnauthorizedUserException extends RuntimeException {

    private final String errorMessage;
}
