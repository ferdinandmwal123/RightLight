
        package com.wsv.right_light_wsv;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyReports implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("damaged")
    @Expose
    private Boolean damaged;
    private final static long serialVersionUID = 1810484073884611487L;

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
     * @param cost
     * @param rentDate
     * @param late
     * @param damaged
     * @param id
     * @param returned
     * @param customer
     */
    public MonthlyReports(Integer id, Integer product, Integer customer, String rentDate, Boolean returned, Boolean late, String returnDate, Integer cost, Boolean damaged) {
        super();
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.rentDate = rentDate;
        this.returned = returned;
        this.late = late;
        this.returnDate = returnDate;
        this.cost = cost;
        this.damaged = damaged;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getDamaged() {
        return damaged;
    }

    public void setDamaged(Boolean damaged) {
        this.damaged = damaged;
    }

}