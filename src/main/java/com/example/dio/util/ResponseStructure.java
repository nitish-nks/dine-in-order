package com.example.dio.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {

    /**
     * HTTP status code of the response.
     */
    private int status;

    /**
     * A descriptive message about the response.
     */
    private String message;

    /**
     * The data payload of the response.
     */
    private T data;


    /**
     * Creates a new {@code ResponseStructure} instance with the given status, message, and data.
     *
     * @param status  the {@link HttpStatus} of the response
     * @param message a descriptive message
     * @param data    the data payload to be included in the response
     * @param <T>     the type of the data payload
     * @return a new {@code ResponseStructure} instance
     */
    public static <T> ResponseStructure<T> create(HttpStatus status, String message, T data) {
        ResponseStructure<T> response = new ResponseStructure<>();
        response.status = status.value();
        response.message = message;
        response.data = data;
        return response;
    }

}
