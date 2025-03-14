package com.example.dio.controller;

import com.example.dio.dto.request.FoodRequest;
import com.example.dio.dto.response.FoodResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.service.FoodService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Food Controller", description = "Food API Endpoints Dealing with Food data.")
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurants/{restaurantId}/foods")
    public ResponseEntity<ResponseStructure<FoodResponse>> registerFood(@RequestBody @Valid FoodRequest foodRequest,
                                                                        @PathVariable Restaurant restaurantId) {
        FoodResponse foodResponse = foodService.registerFoods(foodRequest, restaurantId);
        return ResponseBuilder.success(HttpStatus.CREATED, "Food is Created", foodResponse);
    }

    @GetMapping("/food/categories")
    public ResponseEntity<ResponseStructure<List<FoodResponse>>> getFoodsByCategories(
            @RequestParam("category") List<String> categories) {
        List<FoodResponse> foodResponses = foodService.getFoodsByCategories(categories);

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ResponseStructure.create(HttpStatus.FOUND, "Food items Found", foodResponses));
    }
}
