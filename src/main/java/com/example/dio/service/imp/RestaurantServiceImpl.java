package com.example.dio.service.imp;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.exception.UnauthorizedUserException;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import com.example.dio.repository.CuisineTypeRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final CuisineTypeRepository cuisineTypeRepository;

    @Override
    public RestaurantResponse registerRestaurant(RestaurantRequest restaurantRequest, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("User not found with id " + userId));

        if (user instanceof Admin admin) {
            Restaurant restaurant = restaurantMapper.mapToRestaurantEntity(restaurantRequest);
            restaurant.setAdmin(admin);
            List<CuisineType> cuisineTypes = this.creteNonExixtingCuisine(restaurant.getCuisineTypes());
            restaurant.setCuisineTypes(cuisineTypes);
            restaurantRepository.save(restaurant);
            return restaurantMapper.mapToRestaurantResponse(restaurant);
        } else
            throw new UnauthorizedUserException("Only admin users can create a restaurant");

    }

    private List<CuisineType> creteNonExixtingCuisine(List<CuisineType> cuisineTypes) {
        return cuisineTypes.stream()
                .map(type -> cuisineTypeRepository.findById(type.getCuisineName())
                        .orElseGet(() -> cuisineTypeRepository.save(type)))
                .toList();
    }
}