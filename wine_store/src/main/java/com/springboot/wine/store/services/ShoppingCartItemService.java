package com.springboot.wine.store.services;


import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;

import java.util.List;

public interface ShoppingCartItemService {
    public void addWineItem(Long wineId, int quantity, String email);
    public void removeWineItem(CartItem cartItem);
    public List<CartItem> getAllCartItemList();
    public List<CartItem> getCustomerCartItemList(String email);
}
