package com.springboot.wine.store.mappers;


import com.springboot.wine.store.dtos.CustomerOrderDTO;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.CustomerOrder;
import com.springboot.wine.store.entities.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerOrderMapper {
    CustomerOrderMapper INSTANCE = Mappers.getMapper(CustomerOrderMapper.class);
    CustomerOrder dtoToCustomerOrder(CustomerOrderDTO customerOrderDTO);
}
