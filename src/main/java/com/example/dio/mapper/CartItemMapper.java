package com.example.dio.mapper;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponse mapToCartItemResponse(CartItem cartItem);
    List<CartItemResponse> mapToCartItemResponses(List<CartItem> cartItems);
}
