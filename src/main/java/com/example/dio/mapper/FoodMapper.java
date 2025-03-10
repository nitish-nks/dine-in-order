package com.example.dio.mapper;

import com.example.dio.dto.request.FoodRequest;
import com.example.dio.dto.response.FoodResponse;
import com.example.dio.model.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food mapToFoodEntity(FoodRequest foodRequest);

    FoodResponse mapToFoodResponse(Food savedFood);
}
