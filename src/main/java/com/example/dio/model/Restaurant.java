package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private String Address;
    private long contactNumber;
    private String contactEmail;
    private LocalTime opensAt;
    private LocalTime closeAt;
    private DietType dietType;
    private LocalTime createdAt;
    private LocalTime modifiedAt;

    @ManyToMany(mappedBy = "restaurantId", fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;


}
