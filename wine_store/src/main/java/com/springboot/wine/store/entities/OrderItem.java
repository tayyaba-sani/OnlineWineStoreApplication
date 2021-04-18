package com.springboot.wine.store.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OrderItem extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date orderDate;
    private Date shipDate;
    private Float price;
    private String status;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERORDER_ID")
    private CustomerOrder customerOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
