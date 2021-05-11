package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.mappers.WineMapper;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WineImpl implements WineService {


    @Autowired
    WineRepository wineRepository;


    public WineDTO addWine(Wine wine) {
        if (CommonUtils.isNullOrEmpty(wine))
            throw new BusinessCaseException(Constants.WINE_DETAILS_NOT_COMPLETED, this.getClass().toString());
        else {
            if (wine.getCountry() != "" && wine.getCountry() != null && wine.getName() != ""
                    && wine.getName() != null) {
                return WineMapper.INSTANCE.WineToDto(wineRepository.save(wine));
            } else {
                throw new BusinessCaseException(Constants.WINE_DETAILS_NOT_COMPLETED, this.getClass().toString());
            }

        }

    }

    public void removeWine(Wine wine) {
        wineRepository.deleteById(wine.getId());
    }

    public List<WineDTO> getWineFindAll() {
        List<Wine> wineList = wineRepository.findAll();
        return convertListOfWineToWineDto(wineList);
    }

    public List<WineDTO> getWineFindByYear(int year) {


        List<Wine> wineList = wineRepository.findByYear(year);
        return convertListOfWineToWineDto(wineList);

    }

    public List<WineDTO> getWineFindByCountry(String country) {

        List<Wine> wineList = wineRepository.findByCountry(country.toLowerCase());
        return convertListOfWineToWineDto(wineList);
    }

    public List<WineDTO> getWineFindByVarietal(String varietal) {
        List<Wine> wineList = wineRepository.findByVarietal(varietal.toLowerCase());
        return convertListOfWineToWineDto(wineList);
    }

    public WineDTO findWineById(Long id) {

        return WineMapper.INSTANCE.WineToDto(wineRepository.findById(id).get());

    }

    private List<WineDTO> convertListOfWineToWineDto(List<Wine> wineList) {
        List<WineDTO> wineDtoList = new ArrayList<>();
        for (Wine wine : wineList) {
            WineDTO wineDTO = WineMapper.INSTANCE.WineToDto(wine);
            wineDtoList.add(wineDTO);
        }
        return wineDtoList;
    }

}
