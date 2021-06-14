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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartItemImplTest {

    @Autowired
    ShoppingCartItemService shoppingCartItemService ;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private WineItemRepository wineItemRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private WineRepository wineRepository;

    @BeforeEach
    void setUp() {
        shoppingCartItemService = new ShoppingCartItemImpl(cartItemRepository, wineItemRepository, customerRepository, wineRepository);
    }

    @Test
    void addWineItem() {

        Wine wine = new Wine();
        wine.setName("Whit Wine");
        long wineId = 2;
        when(wineRepository.findById(wineId)).thenReturn(java.util.Optional.of(wine));
        Customer customer = new Customer();
        customer.setFirstName("sanikhan");
        long customerId = 2;
        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(customer));
        WineItem wineItem = new WineItem();
        wineItem.setQuantity(2);
        wineItem.setWine(wine);

        when(wineItemRepository.save(any(WineItem.class))).thenReturn(wineItem);
        CartItem cartItem = new CartItem();
        cartItem.setCustomer(customer);
        cartItem.setWineItem(wineItem);

        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);

        CartItem cartItem1 = shoppingCartItemService.addWineItem(wineId, 2, customerId);
        assertThat(cartItem1).isNotNull();
    }
    @Test
    void addWineItemThrowException() {

        Wine wine = new Wine();
        wine.setName("Whit Wine");
        long wineId = 2;
        when(wineRepository.findById(wineId)).thenThrow(new NoSuchElementException());

        long customerId = 2;
        CartItem cartItem = shoppingCartItemService.addWineItem(wineId, 2, customerId);
        assertThat(cartItem).isEqualTo(null);
    }

    @Test
    void removeWineItem() {
        CartItem cartItem = new CartItem();
        WineItem wineItem = new WineItem();
        wineItem.setId(2);
        cartItem.setWineItem(wineItem);
        cartItem.setId(2);
        shoppingCartItemService.removeWineItem(cartItem);
        verify(wineItemRepository).deleteById(cartItem.getWineItem().getId());
        verify(cartItemRepository).deleteById(cartItem.getId());

    }

    @Test
    void getAllCartItemList() {
        shoppingCartItemService.getAllCartItemList();
        verify(cartItemRepository).findAll();

    }
}