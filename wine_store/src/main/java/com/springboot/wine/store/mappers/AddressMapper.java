package com.springboot.wine.store.mappers;


import com.springboot.wine.store.dtos.AddressDTO;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Address;
import com.springboot.wine.store.entities.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Address dtoToAddress(AddressDTO addressDTO);
}
