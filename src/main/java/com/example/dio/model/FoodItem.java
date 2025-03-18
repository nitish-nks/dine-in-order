package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food_items",indexes = {@Index(name ="idx_foodName",columnList = "foodName")})
@EntityListeners(AuditingEntityListener.class)
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="itemId" )
    private long itemId;

    @Column(name ="foodName" )
    private String foodName;

    @Column(name ="price" )
    private double price;

    @Column(name ="description" )
    private String description;

    @Column(name ="stock" )
    private int stock;

    @Column(name = "availability")
    private String availability;

    @Column(name ="dietType" )
    @Enumerated(EnumType.STRING)
    private DietType dietType;

    @CreatedDate
    @Column(name ="createdAt" )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name ="lastModifiedAt" )
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ManyToOne
    private Cuisine cuisine;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy ="foodItem" )
    private List<CartItem> cartItems;

}
