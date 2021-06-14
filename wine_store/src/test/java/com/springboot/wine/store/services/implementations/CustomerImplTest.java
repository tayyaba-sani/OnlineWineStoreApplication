package com.springboot.wine.store.services.implementations;

import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.entities.*;
import com.springboot.wine.store.repositories.CartItemRepository;
import com.springboot.wine.store.repositories.CustomerOrderRepository;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.repositories.OrderItemRepository;
import com.springboot.wine.store.services.CustomerService;
import com.springboot.wine.store.services.JavaMailService;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerImplTest {


    @Autowired
    CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private CustomerOrderRepository customerOrderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ShoppingCartItemService shoppingCartItemService;
    @Mock
    private JavaMailService javaMailService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerImpl(customerRepository, customerOrderRepository,
                orderItemRepository, shoppingCartItemService,
                javaMailService, cartItemRepository);
    }

    @Test
    void registerCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Tayyaba");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer newCustomer = customerService.registerCustomer(customer);
        assertThat(newCustomer).isNotNull();
        verify(customerRepository).save(customer);
    }

    @Test
    void getCustomerFindByEmail() {
        String email = "tayyaba@yahoo.com";
        when(customerRepository.findCustomerByEmail(email)).thenReturn(new Customer());
        Customer newCustomer = customerService.getCustomerFindByEmail(email);
        assertThat(newCustomer).isNotNull();
        verify(customerRepository).findCustomerByEmail(email);
    }

    @Test
    void getCustomerFindByEmailNotFound() {
        String email = "tayyaba@yahoo.com";
        when(customerRepository.findCustomerByEmail(email)).thenReturn(null);
        Customer newCustomer = customerService.getCustomerFindByEmail(email);
        assertThat(newCustomer).isNull();
        verify(customerRepository).findCustomerByEmail(email);
    }

    @Test
    void getCustomerFindById() {
        long id = 2;
        when(customerRepository.findById(id)).thenReturn(java.util.Optional.of(new Customer()));
        Customer newCustomer = customerService.getCustomerFindById(id);
        assertThat(newCustomer).isNotNull();
        verify(customerRepository).findById(id);
    }

    @Test
    void getCustomerFindByIdThrowException() {
        long id = 2;
        when(customerRepository.findById(id)).thenThrow(new NoSuchElementException());
        Customer newCustomer = customerService.getCustomerFindById(id);
        assertThat(newCustomer).isNull();
        verify(customerRepository).findById(id);
    }

    @Test
    void getCustomerFindAll() {
        customerService.getCustomerFindAll();
        verify(customerRepository).findAll();
    }

    @Test
    void removeCustomer() {
        customerService.removeCustomer(2L);
        verify(customerRepository).deleteById(2L);
    }

    @Test
    void getCustomerCartItemList() {
        List<CartItem> cartItemList = new ArrayList<>();
        when(cartItemRepository.findByCustomer(2L)).thenReturn(cartItemList);
        List<CartItem> newCartItemList = customerService.getCustomerCartItemList(2L);
        assertThat(newCartItemList).isEqualTo(cartItemList);
        verify(cartItemRepository).findByCustomer(2L);
    }

    @Test
    void getCustomerCartItemListThrowException() {
        when(cartItemRepository.findByCustomer(2L)).thenThrow(new NoSuchElementException());
        List<CartItem> newCartItemList = customerService.getCustomerCartItemList(2L);
        assertThat(newCartItemList).isNull();
        verify(cartItemRepository).findByCustomer(2L);
    }

    @Test
    void processOrder() {

        Customer customer = new Customer();
        customer.setEmail("tayyaba@yahoo.com");
        CartItem cartItem = new CartItem();
        WineItem wineItem = new WineItem();
        Wine wine = new Wine();
        wine.setRetailPrice(4F);
        wineItem.setWine(wine);
        cartItem.setWineItem(wineItem);
        cartItem.setCustomer(customer);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        customer.setCartItemList(cartItemList);

        when(customerRepository.findById(2L)).thenReturn(java.util.Optional.of(customer));
        CustomerOrder customerOrder = new CustomerOrder();
        when(customerOrderRepository.save(any(CustomerOrder.class))).thenReturn(customerOrder);
        OrderItem orderItem = new OrderItem();
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);
        doNothing().when(shoppingCartItemService).removeWineItem(cartItem);
        doNothing().when(javaMailService).sendEmail(customer.getEmail());

        String status = customerService.processOrder(2L);
        assertThat(status).isEqualTo(Constants.SUCCESS_ORDER_SUBMITTED);

    }

    @Test
    void processOrderThrowException() {
        when(customerRepository.findById(2L)).thenThrow(new NoSuchElementException());
        String status = customerService.processOrder(2L);
        assertThat(status).isEqualTo(null);

    }

    @Test
    void processOrderThrowBusinessException() {

        Customer customer = new Customer();
        customer.setEmail("tayyaba@yahoo.com");
        CartItem cartItem = new CartItem();
        WineItem wineItem = new WineItem();
        Wine wine = new Wine();
        wine.setRetailPrice(4F);
        wineItem.setWine(wine);
        cartItem.setWineItem(wineItem);
        cartItem.setCustomer(customer);
        List<CartItem> cartItemList = new ArrayList<>();
        customer.setCartItemList(cartItemList);

        when(customerRepository.findById(2L)).thenReturn(java.util.Optional.of(customer));
        assertThatThrownBy(() -> customerService.processOrder(2L)).hasMessage("Given Customer id has no cart items");

    }
}