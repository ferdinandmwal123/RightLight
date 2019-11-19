

package com.wsv.right_light_wsv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Comparator;

@Parcel
public class Customer {

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

    /**
     * No args constructor for use in serialization
     */
    public Customer() {
    }

    /**
     * @param seller
     * @param phoneNumber
     * @param name
     * @param customerId
     */
    public Customer(String name, String phoneNumber, Integer seller, String customerId) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.seller = seller;
        this.customerId = customerId;
    }

    public static final Comparator<Customer> SORT_BY_NAME  = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    };

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