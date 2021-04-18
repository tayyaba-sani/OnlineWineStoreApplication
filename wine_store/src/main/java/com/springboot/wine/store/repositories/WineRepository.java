package com.springboot.wine.store.repositories;


import com.springboot.wine.store.entities.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WineRepository extends JpaRepository<Wine,Long> {

    public List<Wine> findByYear(int year);
    public List<Wine> findByCountry(String country);
    public List<Wine> findByVarietal(String varietal);
}
