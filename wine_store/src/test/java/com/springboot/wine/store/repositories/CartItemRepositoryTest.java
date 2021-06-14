package com.springboot.wine.store.repositories;

import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.WineItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartItemRepositoryTest {


    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WineItemRepository wineItemRepository;

    @Test
    void testHappyPathForFindByCustomer() {

        Customer customer = new Customer();
        customer.setEmail("tayyaba@ayhoo.com");
        customer.setFirstName("Sani");

        Customer newCustomer = customerRepository.save(customer);

        WineItem wineItem = new WineItem();
        wineItem.setQuantity(2);
        wineItemRepository.save(wineItem);

        CartItem cartItem = new CartItem();
        cartItem.setWineItem(wineItem);
        cartItem.setCustomer(customer);

        cartItemRepository.save(cartItem);

        List<CartItem> cartItemList = cartItemRepository.findByCustomer(newCustomer.getId());

        assertThat(cartItemList).isNotNull();

    }

    @Test
    void testFailurePathForFindByCustomer() {

        long customerId = 2;
        List<CartItem> cartItemList = cartItemRepository.findByCustomer(customerId);

        assertThat(cartItemList.size()).isEqualTo(0);

    }
}