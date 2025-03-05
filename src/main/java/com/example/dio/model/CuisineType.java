package com.example.dio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CuisineType {
    @Id
    @Column(name = "cuisine_name")
    private String cuisineName;

    @ManyToMany
    private List<Restaurant> restaurants;
}
