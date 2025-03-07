package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
@Tag(name = "Restaurant Controller", description = "Collection API Endpoints Dealing with restaurant data.")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> createRestaurant(
            @PathVariable long userId,
            @Valid @RequestBody RestaurantRequest restaurantRequest) {

        RestaurantResponse response = restaurantService.registerRestaurant(restaurantRequest, userId);
        return ResponseBuilder.success(HttpStatus.CREATED, "Restaurant created successfully", response);
    }
}
