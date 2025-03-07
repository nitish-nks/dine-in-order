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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    //not know
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "restaurant_cuisine",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_name")
    )
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch =FetchType.LAZY)
    private Admin admin;

    @OneToMany
    private List<Tables> tables;


}