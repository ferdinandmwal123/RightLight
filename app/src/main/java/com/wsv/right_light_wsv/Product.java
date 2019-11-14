package com.wsv.right_light_wsv;

public class Product {
    private String category;
    private String product_id;
    private int seller_id;
    private String type;
    private boolean rented;
    private boolean available;
    private boolean damaged;

    public Product(String category, String product_id, String type, int seller_id) {
        this.category = category;
        this.product_id = product_id;
        this.type = type;
        this.rented = false;
        this.available = true;
        this.damaged = false;
    }

    public Product() {

    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }
}
