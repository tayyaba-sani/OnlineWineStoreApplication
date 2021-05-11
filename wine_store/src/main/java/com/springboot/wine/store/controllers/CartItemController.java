package com.springboot.wine.store.controllers;


import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.dtos.ResponseDTO;
import com.springboot.wine.store.dtos.ResponseHandler;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartItems")
public class CartItemController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @PostMapping("/addToCartItem")
    public ResponseHandler<ResponseDTO> addToCart(@RequestBody CartItemDTO cartItemDTO) {
        shoppingCartItemService.addWineItem(cartItemDTO.getWineId(), cartItemDTO.getQuantity(), cartItemDTO.getEmail());
        return ResponseDTO.responseSuccessful();
    }

    @GetMapping("/getCustomerCartItems/{email}")
    public ResponseHandler<List<CartItem>> getCustomerCartItem(@PathVariable("email") String email) {
        return ResponseDTO.responseSuccessful(shoppingCartItemService.getCustomerCartItemList(email));
    }

}
