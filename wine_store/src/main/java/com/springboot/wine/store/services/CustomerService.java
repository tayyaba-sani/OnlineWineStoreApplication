package com.springboot.wine.store.services;


import com.springboot.wine.store.entities.Customer;

import java.util.List;

public interface CustomerService {

    public Customer registerCustomer(Customer customer) ;
    public Customer getCustomerFindByEmail(String email) ;
    public Customer getCustomerFindById(Long id) ;
    public List<Customer> getCustomerFindAll() ;
    public void removeCustomer(Long id) ;
}
