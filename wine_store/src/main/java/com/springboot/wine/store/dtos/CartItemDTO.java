package com.springboot.wine.store.dtos;

public class CartItemDTO {

    private long CartItemId;
    private long wineItemId;
    private int quantity;
    private long customerId;
    private long wineId;


    public long getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(long cartItemId) {
        CartItemId = cartItemId;
    }

    public long getWineItemId() {
        return wineItemId;
    }

    public void setWineItemId(long wineItemId) {
        this.wineItemId = wineItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getWineId() {
        return wineId;
    }

    public void setWineId(long wineId) {
        this.wineId = wineId;
    }
}
