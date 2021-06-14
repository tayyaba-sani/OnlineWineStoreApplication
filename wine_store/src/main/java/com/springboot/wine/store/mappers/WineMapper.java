package com.springboot.wine.store.mappers;


import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface WineMapper {

    WineMapper INSTANCE = Mappers.getMapper(WineMapper.class);

        Wine dtoToWine(WineDTO wineDTO);
        WineDTO WineToDto(Wine wine);

    default List<WineDTO> convertListOfWineToWineDto(List<Wine> wineList) {
        List<WineDTO> wineDtoList = new ArrayList<>();
        if(wineList != null) {
            for (Wine wine : wineList) {
                WineDTO wineDTO = WineMapper.INSTANCE.WineToDto(wine);
                wineDtoList.add(wineDTO);
            }
        }
        return wineDtoList;
    }
}
