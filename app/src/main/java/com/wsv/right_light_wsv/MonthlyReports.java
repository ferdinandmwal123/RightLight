
package com.wsv.right_light_wsv;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyReports implements Serializable
{

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
    private final static long serialVersionUID = 3722615226446425018L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MonthlyReports() {
    }

    /**
     * 
     * @param product
     * @param returnDate
     * @param rentDate
     * @param late
     * @param returned
     * @param customer
     */
    public MonthlyReports(Integer product, Integer customer, String rentDate, Boolean returned, Boolean late, String returnDate) {
        super();
        this.product = product;
        this.customer = customer;
        this.rentDate = rentDate;
        this.returned = returned;
        this.late = late;
        this.returnDate = returnDate;
    }

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
