package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.Restaurant;

public interface RestaurantMapper {
    Restaurant mapToRestaurantEntity(RestaurantRequest restaurantRequest);

    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);

}

