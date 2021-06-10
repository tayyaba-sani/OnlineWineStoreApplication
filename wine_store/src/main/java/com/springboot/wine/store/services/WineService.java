package com.springboot.wine.store.services;


import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;

import java.util.List;

public interface WineService {

    Wine addWine(Wine wine);

    void removeWine(Wine wine);

    List<Wine> getWineFindAll();

    List<Wine> getWineFindByYear(int year);

    List<Wine> getWineFindByCountry(String country);

    List<Wine> getWineFindByVarietal(String varietal);

    Wine findWineById(Long id);
}
