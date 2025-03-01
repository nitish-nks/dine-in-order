package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotEmpty(message = "User name can't be null or blank")
    @NotBlank(message = "You can't provide space as name")
    public String username;

    @NotEmpty(message = "Email can't be blank")
    @NotBlank(message = "You can't provide space as email")
    public String email;

    public String password;
    public long phNo;
    public UserRole role;
}
