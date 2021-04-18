package com.springboot.wine.store.repositories;


import com.springboot.wine.store.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    public Customer findCustomerByEmail(String email);
}
