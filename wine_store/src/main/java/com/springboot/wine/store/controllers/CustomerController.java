package com.springboot.wine.store.controllers;


import com.springboot.wine.store.dtos.ResponseDTO;
import com.springboot.wine.store.dtos.ResponseHandler;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseHandler<Customer> createCustomer(@RequestBody Customer customer)
    {
        return ResponseDTO.responseSuccessful(customerService.registerCustomer(customer));
    }

    @GetMapping("/findById/{id}")
    public ResponseHandler<Customer> getCustomerById(@PathVariable("id") long id)
    {

        return ResponseDTO.responseSuccessful(customerService.getCustomerFindById(id));
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseHandler<Customer> getCustomerByEmail(@PathVariable("email") String email)
    {
        return ResponseDTO.responseSuccessful(customerService.getCustomerFindByEmail(email));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseHandler<ResponseDTO> removeCustomerById(@PathVariable("id") long id)
    {
        customerService.removeCustomer(id);
       return ResponseDTO.responseSuccessful();
    }

}
