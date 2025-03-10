package com.example.dio.service;

import com.example.dio.dto.request.FoodRequest;
import com.example.dio.dto.response.FoodResponse;
import com.example.dio.model.Restaurant;
import jakarta.validation.Valid;

public interface FoodService {
    public FoodResponse registerFoods(@Valid FoodRequest foodRequest, Restaurant restaurantId);
}
