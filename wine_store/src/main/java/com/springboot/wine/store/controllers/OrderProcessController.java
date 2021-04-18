package com.springboot.wine.store.controllers;


import com.springboot.wine.store.dtos.ResponseDTO;
import com.springboot.wine.store.dtos.ResponseHandler;
import com.springboot.wine.store.services.OrderProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderProcess")
public class OrderProcessController {

    @Autowired
    OrderProcessService orderProcessService;

    @PostMapping("/submitOrder/{email}")
    public ResponseHandler<String> submitOrder(@PathVariable("email") String email)
    {
        return ResponseDTO.responseSuccessful(orderProcessService.processOrder(email));
    }
}
