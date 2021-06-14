package com.springboot.wine.store.dtos;

import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.OrderItem;

import java.util.Date;
import java.util.List;

public class CustomerOrderDTO {


    private Date creationDate;
    private String status;

    private Customer customer;

    private List<OrderItem> orderItems;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
