package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {

    private final String message;
}
