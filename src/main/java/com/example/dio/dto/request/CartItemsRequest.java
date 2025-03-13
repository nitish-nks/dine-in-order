package com.example.dio.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemsRequest {
    @NotNull(message = "Food item ID is required")
    private Long foodItemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
