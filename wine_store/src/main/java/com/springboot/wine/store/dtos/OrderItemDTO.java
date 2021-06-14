package com.springboot.wine.store.dtos;

import com.springboot.wine.store.entities.CustomerOrder;

import java.util.Date;

public class OrderItemDTO {

    private Date orderDate;
    private Date shipDate;
    private Float price;
    private String status;

    private CustomerOrder customerOrders;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerOrder getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(CustomerOrder customerOrders) {
        this.customerOrders = customerOrders;
    }
}
