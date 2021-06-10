package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.WineService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WineImpl implements WineService {


    private WineRepository wineRepository;

    public WineImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Transactional
    public Wine addWine(Wine wine) {
        return wineRepository.save(wine);
    }

    public void removeWine(Wine wine) {
        wineRepository.deleteById(wine.getId());
    }

    public List<Wine> getWineFindAll() {
        List<Wine> wineList = wineRepository.findAll();
        return wineList;
    }

    public List<Wine> getWineFindByYear(int year) {
        List<Wine> wineList = wineRepository.findByYear(year);
        return wineList;
    }

    public List<Wine> getWineFindByCountry(String country) {

        List<Wine> wineList = wineRepository.findByCountry(country.toLowerCase());
        return wineList;
    }

    public List<Wine> getWineFindByVarietal(String varietal) {
        List<Wine> wineList = wineRepository.findByVarietal(varietal.toLowerCase());
        return wineList;
    }

    public Wine findWineById(Long id) {
        try {
            return wineRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }


    }


}
