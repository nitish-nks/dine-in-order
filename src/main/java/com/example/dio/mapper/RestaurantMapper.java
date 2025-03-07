package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant mapToRestaurantEntity(RestaurantRequest restaurantRequest);

    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);

    default String mapToString(CuisineType cuisineType){
        if(cuisineType == null){
            return null;
        }
        return cuisineType.getCuisineName();
    }

    default CuisineType stringToCuisineType(String cuisineName) {
        if (cuisineName == null) {
            return null;
        }
        CuisineType cuisine = new CuisineType();
        cuisine.setCuisineName(cuisineName);
        return cuisine;
    }

}

