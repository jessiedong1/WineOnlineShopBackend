package com.wineshop.model;

public class OrderProductDto {

    private Wine product;
    private int quantity;

    public Wine getProduct() {
        return product;
    }

    public void setProduct(Wine product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
