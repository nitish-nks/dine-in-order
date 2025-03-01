package com.example.dio.model;

import com.example.dio.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "ph_no")
    private long phNo;

    @Column(name = "create_at")
    private LocalDate createdAt;

    @Column(name = "last_modified_at")
    private LocalDate lastModifiedAt;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;


}
