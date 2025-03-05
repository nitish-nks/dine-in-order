package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
@Getter
@Setter
public class RestaurantResponse {
    private long restaurantId;
    private String name;
    private String address;
    private String contactNumber;
    private String contactEmail;
    private LocalTime opensAt;
    private LocalTime closeAt;

    private List<DietType> dietTypes;

    private List<String> cuisineTypes;
}
