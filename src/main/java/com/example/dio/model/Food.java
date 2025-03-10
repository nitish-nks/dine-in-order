package com.example.dio.model;

import com.example.dio.enums.Availability;
import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food_items")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    private String description;

    @Column(nullable = false)
    private Integer stocks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Availability availability;

    @ElementCollection
    @CollectionTable(name = "food_diet_types", joinColumns = @JoinColumn(name = "item_id"))
    @Enumerated(EnumType.STRING)
    private List<DietType> dietTypes;

    @CollectionTable(name = "food_cuisine_types", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "cuisine_type")
    private String cuisineType;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}