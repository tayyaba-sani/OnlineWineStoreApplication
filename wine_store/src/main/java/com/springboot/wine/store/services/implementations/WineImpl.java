package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WineImpl implements WineService {

    private final WineRepository wineRepository;
    Logger logger = LoggerFactory.getLogger(WineImpl.class);

    public WineImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Transactional
    public Wine addWine(Wine wine) {
        logger.info("Service: WineImpl: addWine");
        return wineRepository.save(wine);
    }

    public void removeWine(Wine wine) {
        logger.info("Service: WineImpl: removeWine");
        wineRepository.deleteById(wine.getId());
    }

    public List<Wine> getWineFindAll() {
        logger.info("Service: WineImpl: getWineFindAll");
        List<Wine> wineList = wineRepository.findAll();
        return wineList;
    }

    public List<Wine> getWineFindByYear(int year) {
        logger.info("Service: WineImpl: getWineFindByYear");
        List<Wine> wineList = wineRepository.findByYear(year);
        return wineList;
    }

    public List<Wine> getWineFindByCountry(String country) {
        logger.info("Service: WineImpl: getWineFindByCountry");
        List<Wine> wineList = wineRepository.findByCountry(country.toLowerCase());
        return wineList;
    }

    public List<Wine> getWineFindByVarietal(String varietal) {
        logger.info("Service: WineImpl: getWineFindByVarietal");
        List<Wine> wineList = wineRepository.findByVarietal(varietal.toLowerCase());
        return wineList;
    }

    public Wine findWineById(Long id) {
        try {
            logger.info("Service: WineImpl: findWineById");
            return wineRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }


    }


}
