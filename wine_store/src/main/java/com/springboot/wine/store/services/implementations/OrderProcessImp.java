package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.entities.*;
import com.springboot.wine.store.repositories.CustomerOrderRepository;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.repositories.OrderItemRepository;
import com.springboot.wine.store.services.JavaMailService;
import com.springboot.wine.store.services.OrderProcessService;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderProcessImp implements OrderProcessService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ShoppingCartItemService shoppingCartItemService;

    @Autowired
    JavaMailService javaMailService;

    public String processOrder(String email) {

        Customer customer = customerRepository.findCustomerByEmail(email);

        if(CommonUtils.isNullOrEmpty(customer))
        {
            throw new BusinessCaseException(Constants.CUSTOMER_DOES_NOT_EXIST,this.getClass().toString());
        }
        else
        {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setCustomer(customer);
            customerOrder.setCreationDate(new Date());
            customerOrder = customerOrderRepository.save(customerOrder);

            List<CartItem> cartItems = customer.getCartItemList();
            for(CartItem cartItem : cartItems)
            {
                OrderItem orderItem = new OrderItem();
                Wine wine = cartItem.getWineItem().getWine();
                orderItem.setPrice(wine.getRetailPrice() * cartItem.getWineItem().getQuantity());
                orderItem.setStatus("Purchase Order sent for processing to the process queue");
                orderItem.setOrderDate(new Date());
                orderItem.setShipDate(new Date());
                orderItem.setCustomerOrders(customerOrder);
                orderItemRepository.save(orderItem);
            }
            for(CartItem cartItem: cartItems)
            {
                shoppingCartItemService.removeWineItem(cartItem);
            }
            javaMailService.sendEmail(customer.getEmail());
            return Constants.SUCCESS_ORDER_SUBMITTED;
        }

    }

}
