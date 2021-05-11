package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.entities.WineItem;
import com.springboot.wine.store.mappers.CartItemMapper;
import com.springboot.wine.store.mappers.WineMapper;
import com.springboot.wine.store.repositories.CartItemRepository;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.repositories.WineItemRepository;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShoppingCartItemImpl implements ShoppingCartItemService {


    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    WineItemRepository wineItemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WineRepository wineRepository;

    public void addWineItem(Long wineId, int quantity, String email) {
        WineItem wineItem = new WineItem();
        Wine wine = new Wine();
        CartItem cartItem = new CartItem();
        Customer customer = new Customer();

        wine = wineRepository.findById(wineId).get();
        customer = customerRepository.findCustomerByEmail(email);

        if(CommonUtils.isNullOrEmpty(wine))
        {
            if(quantity == 0 || quantity < 0) {
                throw new BusinessCaseException(Constants.QUANTITY_IS_EMPTY, this.getClass().toString());
            }
            if(CommonUtils.isNullOrEmpty(customer)){
                throw new BusinessCaseException(Constants.CUSTOMER_DOES_NOT_EXIST,this.getClass().toString());
            }
            else
            {
                throw new BusinessCaseException(Constants.WINE_DOES_NOT_EXIST,this.getClass().toString());
            }
        }
        else{
            wineItem.setWine(wine);
            wineItem.setQuantity(quantity);

            wineItem = wineItemRepository.save(wineItem);

            cartItem.setCustomer(customer);
            cartItem.setWineItem(wineItem);

            cartItemRepository.save(cartItem);
        }


    }

    public void removeWineItem(CartItem cartItem)
    {
        wineItemRepository.deleteById(cartItem.getWineItem().getId());
        cartItemRepository.delete(cartItem);
    }
    public List<CartItemDTO> getAllCartItemList()
    {
        List<CartItem> cartItemList = cartItemRepository.findAll();
        return convertCartItemIntoCartItemDTOList(cartItemList);
    }
    public List<CartItemDTO> getCustomerCartItemList(String email)
    {
        Customer customer = new Customer();
        customer = customerRepository.findCustomerByEmail(email);
        List<CartItem> cartItemList = cartItemRepository.findByCustomer(customer.getId());
        return convertCartItemIntoCartItemDTOList(cartItemList);
    }

    private List<CartItemDTO> convertCartItemIntoCartItemDTOList(List<CartItem> cartItemList)
    {
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            CartItemDTO cartItemDTO = CartItemMapper.INSTANCE.CartItemToDto(cartItem);
            cartItemDTOList.add(cartItemDTO);
        }
        return cartItemDTOList;
    }

}
