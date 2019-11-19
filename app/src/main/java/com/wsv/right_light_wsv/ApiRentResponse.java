package com.wsv.right_light_wsv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiRentResponse {

    @SerializedName("product")
    @Expose
    private Integer product;
    @SerializedName("customer")
    @Expose
    private Integer customer;
    @SerializedName("rent_date")
    @Expose
    private String rentDate;
    @SerializedName("returned")
    @Expose
    private Boolean returned;
    @SerializedName("late")
    @Expose
    private Boolean late;
    @SerializedName("return_date")
    @Expose
    private String returnDate;

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Boolean getLate() {
        return late;
    }

    public void setLate(Boolean late) {
        this.late = late;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}