package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    public long userId;
    public String username;
    public UserRole role;
    public LocalDate createdAt;
    public LocalDate lastModifiedAt;

}
