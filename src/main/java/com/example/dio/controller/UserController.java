package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        UserResponse response = userService.registerUser(registrationRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User Created", response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable long userId) {
        UserResponse response = userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found", response);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable long userId, @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUserById(userId, userRequest);
        return ResponseBuilder.success(HttpStatus.OK, "User Updated", response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseStructure<String>> deleteUserById(@PathVariable long userId) {
        userService.deleteUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Deleted ", "user");
    }


}

