package com.example.dio.service.imp;

import com.example.dio.dto.request.FoodRequest;
import com.example.dio.dto.response.FoodResponse;
import com.example.dio.enums.Availability;
import com.example.dio.mapper.FoodMapper;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Food;
import com.example.dio.model.Restaurant;
import com.example.dio.repository.CuisineTypeRepository;
import com.example.dio.repository.FoodRepository;
import com.example.dio.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final CuisineTypeRepository cuisineTypeRepository;

    @Override
    @Transactional
    public FoodResponse registerFoods(FoodRequest foodRequest, Restaurant restaurant) {
        String cuisineName = foodRequest.getCuisineType();
        CuisineType cuisineType = cuisineTypeRepository.findById(cuisineName)
                .orElseGet(() -> {
                    CuisineType newCuisine = new CuisineType();
                    newCuisine.setCuisineName(cuisineName);
                    return cuisineTypeRepository.save(newCuisine);
                });
        if (restaurant.getCuisineTypes() == null) {
            restaurant.setCuisineTypes(new ArrayList<>());
        }
        if (!restaurant.getCuisineTypes().contains(cuisineType)) {
            restaurant.getCuisineTypes().add(cuisineType);
        }
        Food food = foodMapper.mapToFoodEntity(foodRequest);
        food.setRestaurant(restaurant);
        food.setCuisineType(cuisineName);
        if (food.getStocks() > 0) {
            food.setAvailability(Availability.AVAILABLE);
        } else {
            food.setAvailability(Availability.OUT_OF_STOCK);
        }
        food.setCreatedAt(LocalDateTime.now());
        food.setLastModifiedAt(LocalDateTime.now());
        Food savedFood = foodRepository.save(food);
        return foodMapper.mapToFoodResponse(savedFood);
    }
}
