package com.springboot.wine.store.repositories;


import com.springboot.wine.store.entities.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {

    @Query("select w from Wine w where w.year = ?1")
    List<Wine> findByYear(int year);

    @Query("select w from Wine w where lower(w.country) = ?1")
    List<Wine> findByCountry(String country);

    @Query("select w from Wine w where lower(w.varietal) = ?1")
    List<Wine> findByVarietal(String varietal);

}
