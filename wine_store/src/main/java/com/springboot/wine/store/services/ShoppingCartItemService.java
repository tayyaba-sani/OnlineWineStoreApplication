package com.springboot.wine.store.services;


import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;

import java.util.List;

public interface ShoppingCartItemService {
    public CartItem addWineItem(Long wineId, int quantity, long customerId);
    public void removeWineItem(CartItem cartItem);
    public List<CartItemDTO> getAllCartItemList();
//    public List<CartItemDTO> getCustomerCartItemList(String email);
}
