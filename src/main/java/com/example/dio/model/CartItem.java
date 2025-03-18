package com.example.dio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private long cartItemId;

    @Column(name ="quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "is_ordered")
    private boolean isOrdered;

    @ManyToOne
    private FoodItem foodItem;

    @ManyToOne
    private RestaurantTable restaurantTable;



}
