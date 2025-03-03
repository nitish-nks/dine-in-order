package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotEmpty(message = "User name can't be null or blank")
    @NotBlank(message = "You can't provide space as name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username Can only contain Alphabets,Numbers and Underscore")
    public String username;

    @NotEmpty(message = "Email can't be blank")
    @NotBlank(message = "You can't provide space as email")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in a valid format, containing a username, \"@\" symbol, domain name, and a valid top-level domain (e.g., .com, .net, .org).")
    public String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{1,12}$", message = "Password must be 1-12 characters long, containing at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&).")
    public String password;

    @Pattern(regexp = "^[7-9]\\d{9}$", message = "Invalid Phone Number")
    public String phNo;

    public UserRole role;
}
