package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.dto.response.UserResponse;
import jakarta.validation.Valid;

public interface UserService {

    /**
     * Registers a new user based on the provided registration details.
     *
     * @param user the registration request containing user information
     * @return a {@link UserResponse} representing the newly created user
     */
    UserResponse registerUser(RegistrationRequest user);

    /**
     * Retrieves user details by their unique identifier.
     *
     * @param id the ID of the user to find
     * @return a {@link UserResponse} containing the user's details
     */
    UserResponse findUserById(long id);

    /**
     * Updates an existing user's information based on their ID.
     *
     * @param userId      the ID of the user to update
     * @param updatedUser the new information for the user
     * @return a {@link UserResponse} with the updated user details
     */
    UserResponse updateUserById(long userId, UserRequest updatedUser);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUserById(long userId);
}
