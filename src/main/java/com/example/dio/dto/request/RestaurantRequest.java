package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    @NotEmpty(message = "User name can't be null or blank")
    @NotBlank(message = "You can't provide space as name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username Can only contain Alphabets,Numbers and Underscore")
    private String name;

    @NotBlank(message = "Address is required")
    @NotEmpty(message = "User Address can't be null or blank")
    private String address;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[7-9]\\d{9}$", message = "Contact number must start with 7, 8, or 9 and contain exactly 10 digits")
    private String contactNumber;

    @NotEmpty(message = "Email can't be blank")
    @NotBlank(message = "You can't provide space as email")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in a valid format, containing a username, \"@\" symbol, domain name, and a valid top-level domain (e.g., .com, .net, .org).")
    private String contactEmail;

    @NotNull(message = "Opening time is required")
    private LocalTime opensAt;

    @NotNull(message = "Closing time is required")
    private LocalTime closeAt;

    @NotEmpty(message = "At least one diet type must be specified")
    private List<DietType> dietTypes;

    @NotEmpty(message = "At least one cuisine type must be specified")
    private List<String> cuisineTypes;
}
