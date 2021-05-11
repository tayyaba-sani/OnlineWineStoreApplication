package com.springboot.wine.store.repositories;


import com.springboot.wine.store.entities.WineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineItemRepository extends JpaRepository<WineItem,Long> {
}