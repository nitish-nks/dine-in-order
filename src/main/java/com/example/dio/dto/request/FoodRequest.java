package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodRequest {
    @NotBlank(message = "Food name is required")
    private String name;

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 0, message = "Stocks cannot be negative")
    private int stocks;

    @NotEmpty(message = "At least one diet type must be specified")
    private List<DietType> dietTypes;

    @NotEmpty(message = "At least one cuisine type must be specified")
    private String cuisineType;
}
