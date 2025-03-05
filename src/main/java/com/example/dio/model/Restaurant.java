package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    private long restaurantId;
    private String name;
    private String address;
    private String contactNumber;
    private String contactEmail;
    private LocalTime opensAt;
    private LocalTime closeAt;

    private List<DietType> dietTypes;

    private LocalTime createdAt;
    private LocalTime modifiedAt;

    @ManyToMany(mappedBy = "restaurants", fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch =FetchType.LAZY)
    private Admin admin;


}
