package com.wsv.right_light_wsv;

public class Product {
    private String product_category;
    private String product_id;
    private int seller;
    private String product_type;
    private boolean rented;
    private boolean available;
    private boolean damaged;
    private int id;



    public Product(String product_category, String product_id, String product_type, int seller, int id) {
        this.product_category = product_category;
        this.product_id = product_id;
        this.product_type = product_type;
        this.rented = false;
        this.available = true;
        this.damaged = false;
        this.seller = seller;
        this.id =id;
    }

    public Product() {

    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
