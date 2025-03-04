package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.service.UserService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name = "User Controller", description = "Collection API Endpoints Dealing with user data.")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(description = "This is the end point where user gets register and user must chose from either of the two role he want to create the id"
            , responses = {
            @ApiResponse(responseCode = "201", description = "Data got inserted"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
                    @Content(schema = @Schema(implementation = FieldErrorResponse.class))
            })
    })

    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody@Valid RegistrationRequest registrationRequest) {
        UserResponse response = userService.registerUser(registrationRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User Created", response);
    }

    @GetMapping("/{userId}")
    @Operation(description = "It is use to find the user based on the id you have provided")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable long userId) {
        UserResponse response = userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found", response);
    }

    @PutMapping("/update/{userId}")
    @Operation(description = "It is use to modifies the user based on the id you have provided")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable long userId, @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUserById(userId, userRequest);
        return ResponseBuilder.success(HttpStatus.OK, "User Updated", response);
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(description = "It is use to Delete the user based on the id you have provided")
    public ResponseEntity<ResponseStructure<String>> deleteUserById(@PathVariable long userId) {
        userService.deleteUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Deleted", "user");
    }
}
