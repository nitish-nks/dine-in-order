package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "table_orders")
public class TableOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "orderedAt")
    private LocalTime orderedAt;

    @Column(name = "totalAmount")
    private double totalAmount;

    @OneToMany
    private List<CartItem> cartItems;

    @ManyToOne
    private RestaurantTable table;


}
