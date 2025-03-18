package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final FoodItemRepository foodItemRepository;
    private final RestaurantTableRepository tableRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemResponse createCartItem(Long foodItemId, Long tableId,int quantity) {
        FoodItem foodItem=foodItemRepository.findById(foodItemId)
                .orElseThrow(() ->new RuntimeException("Food item not found with id: "+foodItemId));

        RestaurantTable table=tableRepository.findById(tableId)
                .orElseThrow(()-> new RuntimeException("Table not found with id: "+tableId));

        CartItem cartItem=new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setRestaurantTable(table);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(calculateTotalPrice(foodItem,quantity));
        cartItemRepository.save(cartItem);

        return cartItemMapper.mapToCartItemResponse(cartItem);


    }

    @Override
    public CartItemResponse updateCartItemQuantity(Long cartItemId, int newQuantity) {
        CartItem cartItem=cartItemRepository.findById(cartItemId)
                .orElseThrow(()->new RuntimeException("Cart item not found with id: "+cartItemId));
        cartItemRepository.updateQuantityByCartItemId(cartItemId,newQuantity);
        cartItem.setQuantity(newQuantity);

        cartItem.setTotalPrice(calculateTotalPrice(cartItem.getFoodItem(),newQuantity));
        return cartItemMapper.mapToCartItemResponse(cartItem);
    }

    public double calculateTotalPrice(FoodItem foodItem,int quantity){
        return foodItem.getPrice()*quantity;

    }
}
