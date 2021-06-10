package com.springboot.wine.store.controllers;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.mappers.CartItemMapper;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("cartItems")
public class CartItemController {

    private final ShoppingCartItemService shoppingCartItemService;

    public CartItemController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @PostMapping()
    public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO cartItemDTO) {

        if (Objects.isNull(cartItemDTO)) {
            throw new BusinessCaseException(Constants.CART_ITEM_DETAIL_NOT_COMPLETED, this.getClass().toString());
        } else {
            CartItemDTO dto = CartItemMapper.INSTANCE.CartItemToDto(shoppingCartItemService.addWineItem(cartItemDTO.getWineId(), cartItemDTO.getQuantity(), cartItemDTO.getCustomerId()));
            if (Objects.isNull(dto)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(dto, HttpStatus.OK);
            }
        }

//        shoppingCartItemService.addWineItem(cartItemDTO.getWineId(), cartItemDTO.getQuantity(), cartItemDTO.getCustomerId());
//        return ResponseDTO.responseSuccessful();
    }
//
//    @GetMapping("/getCustomerCartItems/{email}")
//    public ResponseHandler<List<CartItem>> getCustomerCartItem(@PathVariable("email") String email) {
//        return ResponseDTO.responseSuccessful(shoppingCartItemService.getCustomerCartItemList(email));
//    }

}
