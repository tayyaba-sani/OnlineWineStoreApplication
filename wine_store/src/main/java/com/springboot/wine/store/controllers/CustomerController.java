package com.springboot.wine.store.controllers;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.dtos.CustomerDTO;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.mappers.CartItemMapper;
import com.springboot.wine.store.mappers.CustomerMapper;
import com.springboot.wine.store.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("Controller: CustomerController: createCustomer: Start");
        if (Objects.isNull(customerDTO.getEmail())) {
            throw new BusinessCaseException(Constants.CUSTOMER_DETAIL_NOT_COMPLETED, this.getClass().toString());
        } else {
            Customer customer = CustomerMapper.INSTANCE.dtoToCustomer(customerDTO);
            Customer newCustomer = customerService.registerCustomer(customer);
            CustomerDTO dto = CustomerMapper.INSTANCE.CustomerToDto(newCustomer);
            if (Objects.isNull(dto)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Controller: CustomerController: createCustomer: End");
                return new ResponseEntity(dto, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customer-id") long id) {
        logger.info("Controller: CustomerController: getCustomerById: Start");
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.CustomerToDto(customerService.getCustomerFindById(id));
        if (Objects.isNull(customerDTO)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: CustomerController: getCustomerById: End");
            return new ResponseEntity(customerDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable("email") String email) {
        logger.info("Controller: CustomerController: getCustomerByEmail: Start");
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.CustomerToDto(customerService.getCustomerFindByEmail(email));
        if (Objects.isNull(customerDTO)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: CustomerController: getCustomerByEmail: End");
            return new ResponseEntity(customerDTO, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity removeCustomerById(@PathVariable("customer-id") long id) {
        logger.info("Controller: CustomerController: removeCustomerById: Start");
        customerService.removeCustomer(id);
        logger.info("Controller: CustomerController: removeCustomerById: End");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{customer-id}/cart-items")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable("customer-id") long id) {
        logger.info("Controller: CustomerController: getCartItems: Start");
        List<CartItemDTO> cartItemList = CartItemMapper.INSTANCE.convertCartItemIntoCartItemDTOList(customerService.getCustomerCartItemList(id));
        if (cartItemList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: CustomerController: getCartItems: End");
            return new ResponseEntity(cartItemList, HttpStatus.OK);
        }
    }

    @GetMapping("/{customer-id}/submit-order")
    public ResponseEntity<String> submitOrder(@PathVariable("customer-id") long id) {
        logger.info("Controller: CustomerController: submitOrder: Start");
        String status = customerService.processOrder(id);
        if (status == null || status.isEmpty()) {
            throw new BusinessCaseException(Constants.CUSTOMER_DOES_NOT_EXIST, this.getClass().toString());
        } else {
            logger.info("Controller: CustomerController: submitOrder: End");
            return new ResponseEntity(status, HttpStatus.OK);
        }
    }

}
