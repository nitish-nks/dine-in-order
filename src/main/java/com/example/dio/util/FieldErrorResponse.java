package com.example.dio.util;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FieldErrorResponse extends SimpleErrorResponse {
    private FieldError fieldError;

    @Getter
    @Builder
    public static class FieldError {
        private String message;
        private Object rejectedVlaue;
        private String feilds;

    }
}