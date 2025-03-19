package com.example.dio.service.imp;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UnauthorizedUserException;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Restaurant;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Creates a new {@link User} instance based on the provided {@link UserRole}.
     *
     * @param role the role of the user to be created
     * @return a new instance of {@link Admin} if the role is ADMIN,
     * {@link Staff} if the role is STAFF, otherwise throws a RuntimeException
     */
    private static User createUserByRole(UserRole role) {
        User user;

        switch (role) {
            case ADMIN -> user = new Admin();
            case STAFF -> user = new Staff();
            default -> throw new RuntimeException("Failed to register user ,invalid user");
        }
        return user;
    }

    /**
     * Registers a new user with the given registration details.
     *
     * @param registrationRequest the registration details containing the user's information
     * @return a {@link UserResponse} representing the registered user
     */
    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {

        User user = this.createUserByRole(registrationRequest.getRole());

        userMapper.mapToUserEntity(registrationRequest, user);
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);

    }

    /**
     * Finds a user by their unique identifier.
     *
     * @param userId the unique identifier of the user
     * @return a {@link UserResponse} representing the found user
     * @throws UserNotFoundByIdException if the user is not found
     */
    @Override
    public UserResponse findUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user,user not found by id"));
        return userMapper.mapToUserResponse(user);
    }

    /**
     * Updates an existing user identified by their unique identifier with new information.
     *
     * @param userId      the unique identifier of the user to update
     * @param userRequest the updated user details
     * @return a {@link UserResponse} representing the updated user
     * @throws UserNotFoundByIdException if the user is not found
     */
    @Override
    public UserResponse updateUserById(long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user ,user not found by id"));

        userMapper.mapToUserEntity(userRequest, user);
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    /**
     * Deletes a user identified by their unique identifier.
     *
     * @param userId the unique identifier of the user to delete
     * @throws UserNotFoundByIdException if the user is not found
     */
    @Override
    public void deleteUserById(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundByIdException("Failed to delete user, user not found by id");
        }
    }

}
