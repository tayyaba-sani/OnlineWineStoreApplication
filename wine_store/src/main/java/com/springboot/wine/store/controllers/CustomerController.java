package com.springboot.wine.store.controllers;


import com.springboot.wine.store.dtos.CustomerDTO;
import com.springboot.wine.store.dtos.ResponseDTO;
import com.springboot.wine.store.dtos.ResponseHandler;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.mappers.CustomerMapper;
import com.springboot.wine.store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseHandler<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {

        Customer customer = CustomerMapper.INSTANCE.dtoToCustomer(customerDTO);
        return ResponseDTO.responseSuccessful(customerService.registerCustomer(customer));
    }

    @GetMapping("/findById/{id}")
    public ResponseHandler<CustomerDTO> getCustomerById(@PathVariable("id") long id) {

        return ResponseDTO.responseSuccessful(customerService.getCustomerFindById(id));
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseHandler<CustomerDTO> getCustomerByEmail(@PathVariable("email") String email) {
        return ResponseDTO.responseSuccessful(customerService.getCustomerFindByEmail(email));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseHandler removeCustomerById(@PathVariable("id") long id) {
        customerService.removeCustomer(id);
        return ResponseDTO.responseSuccessful();
    }

}
