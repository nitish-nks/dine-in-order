package com.example.dio.dto.response;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.CartItem;
import com.example.dio.model.RestaurantTable;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private OrderStatus orderStatus;

    private LocalTime orderedAt;

    private double totalAmount;

    private List<CartItemResponse> cartItems;

//    private RestaurantTable table;
}
