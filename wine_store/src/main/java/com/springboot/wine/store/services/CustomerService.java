package com.springboot.wine.store.services;


import com.springboot.wine.store.dtos.CustomerDTO;
import com.springboot.wine.store.entities.Customer;

import java.util.List;

public interface CustomerService {

    public CustomerDTO registerCustomer(Customer customer) ;
    public CustomerDTO getCustomerFindByEmail(String email) ;
    public CustomerDTO getCustomerFindById(Long id) ;
    public List<CustomerDTO> getCustomerFindAll() ;
    public void removeCustomer(Long id) ;
}
