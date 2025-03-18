package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;

import java.util.List;

public interface FoodItemService {
    FoodItemResponse createFoodItem(Long restaurantId, FoodItemRequest foodItemRequest);

    List<FoodItemResponse> getFoodItemsByCategories(List<String> categories);
}
