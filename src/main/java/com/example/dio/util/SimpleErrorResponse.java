package com.example.dio.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleErrorResponse {

    private String type;
    private int status;
    private String message;
}
