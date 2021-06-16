package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.entities.WineItem;
import com.springboot.wine.store.repositories.CartItemRepository;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.repositories.WineItemRepository;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShoppingCartItemImpl implements ShoppingCartItemService {
    private final CartItemRepository cartItemRepository;
    private final WineItemRepository wineItemRepository;
    private final CustomerRepository customerRepository;
    private final WineRepository wineRepository;
    Logger logger = LoggerFactory.getLogger(ShoppingCartItemImpl.class);

    public ShoppingCartItemImpl(CartItemRepository cartItemRepository, WineItemRepository wineItemRepository,
                                CustomerRepository customerRepository, WineRepository wineRepository) {
        this.cartItemRepository = cartItemRepository;
        this.wineItemRepository = wineItemRepository;
        this.customerRepository = customerRepository;
        this.wineRepository = wineRepository;
    }

    @Transactional
    public CartItem addWineItem(long wineId, int quantity, long customerId) {
        logger.info("Service: ShoppingCartItemImpl: addWineItem: Start");
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

            logger.info("Service: ShoppingCartItemImpl: addWineItem: End");
            return cartItemRepository.save(cartItem);

        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }

    @Transactional
    public void removeWineItem(CartItem cartItem) {
        logger.info("Service: ShoppingCartItemImpl: removeWineItem: Start");
        wineItemRepository.deleteById(cartItem.getWineItem().getId());
        cartItemRepository.deleteById(cartItem.getId());
        logger.info("Service: ShoppingCartItemImpl: removeWineItem: End");
    }

    public List<CartItem> getAllCartItemList() {
        logger.info("Service: ShoppingCartItemImpl: getAllCartItemList");
        return cartItemRepository.findAll();
    }

}
