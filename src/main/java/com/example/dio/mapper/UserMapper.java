package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;
@Mapper(componentModel = "spring")
public interface UserMapper {
    void mapToUserEntity(RegistrationRequest registrationRequest,@MappingTarget User user) ;

    void mapToUserEntity(UserRequest userRequest,@MappingTarget User user) ;

    UserResponse mapToUserResponse(User user);

}
