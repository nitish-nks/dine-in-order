package com.example.dio.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cuisines")
@Getter
@Setter
public class Cuisine {
    @Id
    @Column(name = "cuisine",unique = true)
    private String cuisine;

    @ManyToMany(mappedBy = "cuisines",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "cuisine")
    private List<FoodItem> foodItems;
}
