package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Admins")
public class Admin extends User {
    @OneToMany(mappedBy = "admin")
    private List<Restaurant> restaurants;
}