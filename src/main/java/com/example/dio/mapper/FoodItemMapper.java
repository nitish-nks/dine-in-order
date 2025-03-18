package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.Cuisine;
import com.example.dio.model.FoodItem;
import com.example.dio.repository.CuisineRepository;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel ="spring")
public interface FoodItemMapper {

    FoodItem mapToFoodItem(FoodItemRequest foodItemRequest);

    FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);


    default Cuisine mapToCuisineType(String cuisine){
        Cuisine newCuisine = new Cuisine();
        newCuisine.setCuisine(cuisine);
        return newCuisine;
    }

    default List<Cuisine> mapCuisines(List<String> cuisineNames) {
        if (cuisineNames == null || cuisineNames.isEmpty()) {
            return List.of();
        }

        return cuisineNames.stream()
                .map(this::mapToCuisineType)
                .collect(Collectors.toList());
    }

    default Category mapToCategory(String category) {
        Category category1 = new Category();
        category1.setCategory(category);
        return category1;
    }

    default List<Category> map(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            return List.of();
        }

        return categories.stream()
                .map(this::mapToCategory)
                .collect(Collectors.toList());
    }
}
