package com.springboot.wine.store.repositories;


import com.springboot.wine.store.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {
}
