package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotNull
    @NotBlank
    public String username;
    public String email;
    public String password;
    public long phNo;
    public UserRole role;
}
