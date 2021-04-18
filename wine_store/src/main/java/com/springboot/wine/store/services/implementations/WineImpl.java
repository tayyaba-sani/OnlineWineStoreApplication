package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WineImpl implements WineService {


    @Autowired
    WineRepository wineRepository;


    public Wine addWine(Wine wine)
    {
        if(CommonUtils.isNullOrEmpty(wine))
            throw new BusinessCaseException(Constants.WINE_DETAILS_NOT_COMPLETED,this.getClass().toString());
        else
        {
            if(wine.getCountry() != "" && wine.getCountry() != null && wine.getName() != ""
            && wine.getName() != null)
            {
                return wineRepository.save(wine);
            }
            else
            {
                throw new BusinessCaseException(Constants.WINE_DETAILS_NOT_COMPLETED,this.getClass().toString());
            }

        }

    }

    public void removeWine(Wine wine) {
        wineRepository.deleteById(wine.getId());
    }

    public List<Wine> getWineFindAll() {
       return  wineRepository.findAll();
    }
    public List<Wine> getWineFindByYear(int year) {
       return wineRepository.findByYear(year);
    }
    public List<Wine> getWineFindByCountry(String country) {

        return wineRepository.findByCountry(country);
    }
    public List<Wine> getWineFindByVarietal(String varietal) {
        return wineRepository.findByVarietal(varietal);
    }
    public Wine findWineById(Long id){

            return wineRepository.findById(id).get();

    }




}
