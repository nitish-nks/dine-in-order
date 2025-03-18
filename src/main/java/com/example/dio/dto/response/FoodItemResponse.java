package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import com.example.dio.model.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemResponse {

    private long itemId;

    private String foodName;

    private double price;

    private String description;

    private int stock;

    private String availability;

    private DietType dietType;

}
