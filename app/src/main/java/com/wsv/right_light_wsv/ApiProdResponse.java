package com.wsv.right_light_wsv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiProdResponse {

    @SerializedName("product_category")
    @Expose
    private List<String> productCategory = null;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("seller")
    @Expose
    private Integer seller;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rented")
    @Expose
    private Boolean rented;
    @SerializedName("available")
    @Expose
    private Boolean available;
    @SerializedName("damaged")
    @Expose
    private Boolean damaged;
    @SerializedName("product_id")
    @Expose
    private String productId;


    public ApiProdResponse(String productType, Integer seller, String name, Boolean rented, Boolean available, Boolean damaged, String productId) {
        this.productType = productType;
        this.seller = seller;
        this.name = name;
        this.rented = rented;
        this.available = available;
        this.damaged = damaged;
        this.productId = productId;
    }

    public ApiProdResponse(List<String> productCategory, String productType, Integer seller, String name, Boolean rented, Boolean available, Boolean damaged, String productId) {
        this.productCategory = productCategory;
        this.productType = productType;
        this.seller = seller;
        this.name = name;
        this.rented = rented;
        this.available = available;
        this.damaged = damaged;
        this.productId = productId;
    }

    public List<String> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<String> productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getDamaged() {
        return damaged;
    }

    public void setDamaged(Boolean damaged) {
        this.damaged = damaged;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}