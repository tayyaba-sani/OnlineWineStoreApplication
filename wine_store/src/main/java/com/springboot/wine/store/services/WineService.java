package com.springboot.wine.store.services;


import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;

import java.util.List;

public interface WineService {

    WineDTO addWine(Wine wine);

    void removeWine(Wine wine);

    List<WineDTO> getWineFindAll();

    List<WineDTO> getWineFindByYear(int year);

    List<WineDTO> getWineFindByCountry(String country);

    List<WineDTO> getWineFindByVarietal(String varietal);

    WineDTO findWineById(Long id);
}
