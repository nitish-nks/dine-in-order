package com.example.dio.service.imp;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
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

    private static User createUserByRole(UserRole role) {
        User user;

        switch (role) {
            case ADMIN -> user = new Admin();
            case STAFF -> user = new Staff();
            default -> throw new RuntimeException("Failed to register user ,invalid user");
        }
        return user;
    }

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {

        User user = this.createUserByRole(registrationRequest.getRole());

        userMapper.mapToUserEntity(registrationRequest, user);
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);

    }

    @Override
    public UserResponse findUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user,user not found by id"));
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user ,user not found by id"));

        userMapper.mapToUserEntity(userRequest, user);
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);

        //______________________________________OLD CODE_______________________________________________________
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()) {
//            User existingUser = optionalUser.get();
//            mapToNewUser(updatedUser, existingUser);
//            return userRepository.save(existingUser);
//        } else {
//            throw new UserNotFoundByIdException("Failed to update user, user not found by id");
//        }
    }

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
