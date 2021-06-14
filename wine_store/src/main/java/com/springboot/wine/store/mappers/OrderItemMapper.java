package com.springboot.wine.store.mappers;

import com.springboot.wine.store.dtos.OrderItemDTO;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.OrderItem;
import com.springboot.wine.store.entities.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
    OrderItem dtoToOrderItem(OrderItemDTO orderItemDTO);
}
