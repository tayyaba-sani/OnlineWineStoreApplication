//package com.springboot.wine.store.services.implementations;
//
//
//import com.springboot.wine.store.common.Utils.Constants;
//import com.springboot.wine.store.entities.*;
//import com.springboot.wine.store.repositories.CustomerOrderRepository;
//import com.springboot.wine.store.repositories.CustomerRepository;
//import com.springboot.wine.store.repositories.OrderItemRepository;
//import com.springboot.wine.store.services.JavaMailService;
//import com.springboot.wine.store.services.ShoppingCartItemService;
//import com.springboot.wine.store.services.SubmitOrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class SubmitOrderImp implements SubmitOrderService {
//
//
//    private CustomerRepository customerRepository;
//
//    private CustomerOrderRepository customerOrderRepository;
//
//    private OrderItemRepository orderItemRepository;
//
//    private ShoppingCartItemService shoppingCartItemService;
//
//    private JavaMailService javaMailService;
//
//    public SubmitOrderImp(CustomerRepository customerRepository,CustomerOrderRepository customerOrderRepository,
//                          OrderItemRepository orderItemRepository,ShoppingCartItemService shoppingCartItemService,
//                          JavaMailService javaMailService)
//    {
//        this.customerOrderRepository = customerOrderRepository;
//        this.customerRepository = customerRepository;
//        this.orderItemRepository = orderItemRepository;
//        this.javaMailService = javaMailService;
//        this.shoppingCartItemService = shoppingCartItemService;
//    }
//
//    public String processOrder(long id) {
//        try {
//            Customer customer = customerRepository.findById(id).get();
//            CustomerOrder customerOrder = new CustomerOrder();
//            customerOrder.setCustomer(customer);
//            customerOrder.setCreationDate(new Date());
//            customerOrder = customerOrderRepository.save(customerOrder);
//
//            List<CartItem> cartItems = customer.getCartItemList();
//            for (CartItem cartItem : cartItems) {
//                OrderItem orderItem = new OrderItem();
//                Wine wine = cartItem.getWineItem().getWine();
//                orderItem.setPrice(wine.getRetailPrice() * cartItem.getWineItem().getQuantity());
//                orderItem.setStatus("Purchase Order sent for processing to the process queue");
//                orderItem.setOrderDate(new Date());
//                orderItem.setShipDate(new Date());
//                orderItem.setCustomerOrders(customerOrder);
//                orderItemRepository.save(orderItem);
//            }
//            for (CartItem cartItem : cartItems) {
//                shoppingCartItemService.removeWineItem(cartItem);
//            }
//            javaMailService.sendEmail(customer.getEmail());
//            return Constants.SUCCESS_ORDER_SUBMITTED;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//}
