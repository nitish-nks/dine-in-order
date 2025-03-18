package com.example.dio.controller;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.service.CartItemService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("${app.base-url}")
public class CartItemController {

    private CartItemService cartItemService;

    @PostMapping("/tables/{tableId}/cart_items/foodItems/{foodItemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> createCartItem(@PathVariable Long foodItemId, @PathVariable long tableId, @RequestParam("quantity") int quantity){
        CartItemResponse cartItemResponse=cartItemService.createCartItem(foodItemId,tableId,quantity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED,"cart item created",cartItemResponse));
    }

    @PatchMapping("/cartItems/{cartItemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> updateCartItemQuantity(@PathVariable Long cartItemId,@RequestParam("quantity") int newQuantity){
        CartItemResponse cartItemResponse=cartItemService.updateCartItemQuantity(cartItemId,newQuantity);
        return ResponseBuilder.ok("cart item quantity updated",cartItemResponse);
    }
}
