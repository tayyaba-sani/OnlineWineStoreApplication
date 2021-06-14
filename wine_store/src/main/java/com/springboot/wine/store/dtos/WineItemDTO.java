package com.springboot.wine.store.dtos;

import com.springboot.wine.store.entities.Wine;

public class WineItemDTO {

    private int quantity;

    private Wine wine;

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
