package com.example.dio.dto.response;

import com.example.dio.enums.Availability;
import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FoodResponse {

    private String name;

    private double price;

    private String description;

    private Availability availability;

    private List<DietType> dietTypes;

    private String cuisineType;

    private LocalDate createdAt;

    private LocalDate lastModifiedAt;
}
