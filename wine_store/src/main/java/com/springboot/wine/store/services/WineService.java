package com.springboot.wine.store.services;


import com.springboot.wine.store.entities.Wine;

import java.util.List;

public interface WineService {

    public Wine addWine(Wine wine);

    public void removeWine(Wine wine) ;

    public List<Wine> getWineFindAll() ;
    public List<Wine> getWineFindByYear(int year) ;
    public List<Wine> getWineFindByCountry(String country) ;
    public List<Wine> getWineFindByVarietal(String varietal) ;
    public Wine findWineById(Long id);
}
