package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.entities.WineItem;
import com.springboot.wine.store.mappers.CartItemMapper;
import com.springboot.wine.store.repositories.CartItemRepository;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.repositories.WineItemRepository;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class ShoppingCartItemImpl implements ShoppingCartItemService {


    private CartItemRepository cartItemRepository;

    private WineItemRepository wineItemRepository;

    private CustomerRepository customerRepository;

    private WineRepository wineRepository;

    public ShoppingCartItemImpl(CartItemRepository cartItemRepository,WineItemRepository wineItemRepository,
                                CustomerRepository customerRepository,WineRepository wineRepository)
    {
        this.cartItemRepository = cartItemRepository;
        this.wineItemRepository = wineItemRepository;
        this.customerRepository = customerRepository;
        this.wineRepository = wineRepository;
    }

    @Transactional
    public CartItem  addWineItem(long wineId, int quantity, long customerId) {

        try {
            WineItem wineItem = new WineItem();
            CartItem cartItem = new CartItem();

            Wine wine = wineRepository.findById(wineId).get();
            Customer customer = customerRepository.findById(customerId).get();

            wineItem.setWine(wine);
            wineItem.setQuantity(quantity);

            wineItem = wineItemRepository.save(wineItem);

            cartItem.setCustomer(customer);
            cartItem.setWineItem(wineItem);

            return cartItemRepository.save(cartItem);

        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }


    }

    @Transactional
    public void removeWineItem(CartItem cartItem) {
        wineItemRepository.deleteById(cartItem.getWineItem().getId());
        cartItemRepository.deleteById(cartItem.getId());
    }

    public List<CartItem> getAllCartItemList() {
        return cartItemRepository.findAll();
//        return CartItemMapper.INSTANCE.convertCartItemIntoCartItemDTOList(cartItemList);
    }

//    public List<CartItemDTO> getCustomerCartItemList(String email) {
//        Customer customer = new Customer();
//        customer = customerRepository.findCustomerByEmail(email);
//        List<CartItem> cartItemList = cartItemRepository.findByCustomer(customer.getId());
//        return convertCartItemIntoCartItemDTOList(cartItemList);
//    }

//    private List<CartItemDTO> convertCartItemIntoCartItemDTOList(List<CartItem> cartItemList) {
//        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
//        for (CartItem cartItem : cartItemList) {
//            CartItemDTO cartItemDTO = CartItemMapper.INSTANCE.CartItemToDto(cartItem);
//            cartItemDTOList.add(cartItemDTO);
//        }
//        return cartItemDTOList;
//    }

}
