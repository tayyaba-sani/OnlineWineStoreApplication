package com.springboot.wine.store.mappers;

import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItem dtoToCartItem(CartItemDTO cartItemDTO);

//    CartItemDTO CartItemToDto(CartItem cartItem);

    default List<CartItemDTO> convertCartItemIntoCartItemDTOList(List<CartItem> cartItemList) {
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        if(cartItemList != null) {
            if (cartItemList.size() != 0) {
                for (CartItem cartItem : cartItemList) {
                    CartItemDTO cartItemDTO = CartItemMapper.INSTANCE.CartItemToDto(cartItem);
                    cartItemDTOList.add(cartItemDTO);
                }
            }
        }
        return cartItemDTOList;
    }
    default CartItemDTO CartItemToDto(CartItem cartItem)
    {
        CartItemDTO cartItemDTO = new CartItemDTO();
        if(cartItem != null) {
            if (cartItem.getWineItem() != null) {
                cartItemDTO.setWineItemId(cartItem.getWineItem().getId());
                cartItemDTO.setQuantity(cartItem.getWineItem().getQuantity());
                if (cartItem.getWineItem().getWine() != null)
                    cartItemDTO.setWineId(cartItem.getWineItem().getWine().getId());
            }
            if (cartItem.getId() != 0)
                cartItemDTO.setCartItemId(cartItem.getId());

            if (cartItem.getCustomer() != null)
                cartItemDTO.setCustomerId(cartItem.getCustomer().getId());
        }

        return cartItemDTO;
    }
}
