package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.entities.*;
import com.springboot.wine.store.repositories.CartItemRepository;
import com.springboot.wine.store.repositories.CustomerOrderRepository;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.repositories.OrderItemRepository;
import com.springboot.wine.store.services.CustomerService;
import com.springboot.wine.store.services.JavaMailService;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class CustomerImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    private final CustomerOrderRepository customerOrderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ShoppingCartItemService shoppingCartItemService;

    private final JavaMailService javaMailService;

    public CustomerImpl(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository,
                        OrderItemRepository orderItemRepository, ShoppingCartItemService shoppingCartItemService,
                        JavaMailService javaMailService, CartItemRepository cartItemRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.javaMailService = javaMailService;
        this.shoppingCartItemService = shoppingCartItemService;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public Customer registerCustomer(Customer customer) {

        return customerRepository.save(customer);
//        if (CommonUtils.isNullOrEmpty(customer))
//            throw new BusinessCaseException(Constants.CUSTOMER_DETAIL_NOT_COMPLETED, this.getClass().toString());
//        else {
//            return CustomerMapper.INSTANCE.CustomerToDto(customerRepository.save(customer));
//        }

    }

    @Override
    public Customer getCustomerFindByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
//        return CustomerMapper.INSTANCE.CustomerToDto(customerRepository.findCustomerByEmail(email));
    }

    @Override
    public Customer getCustomerFindById(Long id) {
        try {
            return customerRepository.findById(id).get();
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
//        return CustomerMapper.INSTANCE.CustomerToDto(customerRepository.findById(id).get());
    }

    @Override
    public List<Customer> getCustomerFindAll() {

        return customerRepository.findAll();
//        return convertListOfCustomerToCustomerDto(customerList);
    }

    @Override
    @Transactional
    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<CartItem> getCustomerCartItemList(long id) {
        try {
            return cartItemRepository.findByCustomer(id);

        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }
    @Transactional
    public String processOrder(long id) {

        try {
            Customer customer = customerRepository.findById(id).get();
            List<CartItem> cartItems = customer.getCartItemList();
            if(cartItems.size() > 0 ) {
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setCustomer(customer);
                customerOrder.setCreationDate(new Date());
                customerOrder = customerOrderRepository.save(customerOrder);


                for (CartItem cartItem : cartItems) {
                    OrderItem orderItem = new OrderItem();
                    Wine wine = cartItem.getWineItem().getWine();
                    orderItem.setPrice(wine.getRetailPrice() * cartItem.getWineItem().getQuantity());
                    orderItem.setStatus("Purchase Order sent for processing to the process queue");
                    orderItem.setOrderDate(new Date());
                    orderItem.setShipDate(new Date());
                    orderItem.setCustomerOrders(customerOrder);
                    orderItemRepository.save(orderItem);
                }
                for (CartItem cartItem : cartItems) {
                    shoppingCartItemService.removeWineItem(cartItem);
                }
                javaMailService.sendEmail(customer.getEmail());
                return Constants.SUCCESS_ORDER_SUBMITTED;
            }else
            {
                throw new BusinessCaseException("Given Customer id has no cart items",this.getClass().toString());
            }
        } catch (NoSuchElementException e) {
            return null;
        }
    }

//    private List<CustomerDTO> convertListOfCustomerToCustomerDto(List<Customer> customerList) {
//        List<CustomerDTO> customerDTOList = new ArrayList<>();
//        for (Customer customer : customerList) {
//            CustomerDTO customerDTO = CustomerMapper.INSTANCE.CustomerToDto(customer);
//            customerDTOList.add(customerDTO);
//        }
//        return customerDTOList;
//    }
}
