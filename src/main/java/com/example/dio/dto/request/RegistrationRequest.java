package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {

    public String username;
    public String email;
    public String password;
    public long phNo;
    public UserRole role;
}
