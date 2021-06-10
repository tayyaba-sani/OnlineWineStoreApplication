package com.springboot.wine.store.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartItem extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonBackReference
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "WINE_ITEM")
    private WineItem wineItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WineItem getWineItem() {
        return wineItem;
    }

    public void setWineItem(WineItem wineItem) {
        this.wineItem = wineItem;
    }
}
