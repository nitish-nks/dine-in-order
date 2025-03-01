package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

public interface UserService {

    UserResponse registerUser(RegistrationRequest user);

    UserResponse findUserById(long id);

    UserResponse updateUserById(long userId, UserRequest updatedUser);

    void deleteUserById(long userId);
}
