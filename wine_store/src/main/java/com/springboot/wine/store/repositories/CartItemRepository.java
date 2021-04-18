package com.springboot.wine.store.repositories;


import com.springboot.wine.store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    public List<CartItem> findByCustomer(Long customerId);
}
