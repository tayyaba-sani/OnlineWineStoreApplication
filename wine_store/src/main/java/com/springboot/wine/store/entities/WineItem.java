package com.springboot.wine.store.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class WineItem extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "WINE")
    private Wine wine;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }
}
