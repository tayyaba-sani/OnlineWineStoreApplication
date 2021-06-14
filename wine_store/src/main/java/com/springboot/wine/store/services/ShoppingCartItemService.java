package com.springboot.wine.store.services;


import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;

import java.util.List;

public interface ShoppingCartItemService {
    public CartItem addWineItem(long wineId, int quantity, long customerId);
    public void removeWineItem(CartItem cartItem);
    public List<CartItem> getAllCartItemList();
//    public List<CartItemDTO> getCustomerCartItemList(String email);
}
