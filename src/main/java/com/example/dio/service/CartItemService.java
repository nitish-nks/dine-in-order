package com.example.dio.service;

import com.example.dio.dto.response.CartItemResponse;



public interface CartItemService {

    CartItemResponse createCartItem(Long foodItemId, Long tableId, int quantity);

    CartItemResponse updateCartItemQuantity(Long cartItemId, int newQuantity);
}
