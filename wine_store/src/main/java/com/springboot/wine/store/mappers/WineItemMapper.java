package com.springboot.wine.store.mappers;

import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.dtos.WineItemDTO;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.entities.WineItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WineItemMapper {
    WineItemMapper INSTANCE = Mappers.getMapper(WineItemMapper.class);
    WineItem dtoToWineItem(WineItemDTO wineItemDTO);
}
