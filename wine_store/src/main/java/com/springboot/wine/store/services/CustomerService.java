package com.springboot.wine.store.services;


import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer registerCustomer(Customer customer);

    Customer getCustomerFindByEmail(String email);

    Customer getCustomerFindById(Long id);

    List<Customer> getCustomerFindAll();

    void removeCustomer(Long id);

    List<CartItem> getCustomerCartItemList(long id);

    String processOrder(long id);
}
