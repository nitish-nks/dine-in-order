package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Builder
@Setter
@Getter
public class UserResponse {
    public long userId;
    public String username;
    public UserRole role;
    public LocalDate createdAt;
    public LocalDate lastModifiedAt;

}
