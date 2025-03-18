package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.*;
import com.example.dio.repository.CategoryRepository;
import com.example.dio.repository.CuisineRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.service.FoodItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {


    private final FoodItemRepository foodItemRepository;

    private final RestaurantRepository restaurantRepository;
    private final FoodItemMapper foodItemMapper;
    private final CuisineRepository cuisineRepository;
    private final CategoryRepository categoryRepository;
    @Transactional
    @Override
    public FoodItemResponse createFoodItem(Long restaurantId, FoodItemRequest foodItemRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));

        Cuisine cuisine = cuisineRepository.findById(foodItemRequest.getCuisine())
                .orElseGet(() -> {
                    Cuisine newCuisine = new Cuisine();
                    newCuisine.setCuisine(foodItemRequest.getCuisine());
                    return cuisineRepository.save(newCuisine);
                });

        // Set the associations
        FoodItem foodItem=foodItemMapper.mapToFoodItem(foodItemRequest);
        foodItem.setRestaurant(restaurant);
        foodItem.setCuisine(cuisine);

        foodItem.setAvailability(updateFoodAvailability(foodItem.getStock(), foodItem.getAvailability()));
        foodItem.setCategories(this.createNonExistingCategory(foodItem.getCategories()));
        // Save food item
        foodItemRepository.save(foodItem);

        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }


    @Override
    public List<FoodItemResponse> getFoodItemsByCategories(List<String> categoryNames) {
        Long categoryCount= (long) categoryNames.size();
        List<FoodItem> foodItems = foodItemRepository.findFoodItemsByCategoryNames(categoryNames,categoryCount);
        return foodItems.stream()
                .map(foodItemMapper::mapToFoodItemResponse)
                .collect(Collectors.toList());
    }



    public String updateFoodAvailability(int stock,String availability){
        availability= (stock>0)? "AVAILABLE":"OUT OF STOCK";
        return availability;
    }

    private List<Category> createNonExistingCategory(List<Category> categories){
        return categories.stream()
                .map(category->
                        categoryRepository.findById(category.getCategory())
                                           .orElseGet(()-> categoryRepository.save(category)))
                .toList();


    }
}
