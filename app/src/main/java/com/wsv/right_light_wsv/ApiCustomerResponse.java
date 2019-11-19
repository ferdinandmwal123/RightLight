package com.wsv.right_light_wsv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiCustomerResponse {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("seller")
    @Expose
    private Integer seller;
    @SerializedName("customer_id")
    @Expose
    private String customerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}