package com.wsv.right_light_wsv;

public class Customer {
    public Customer(String customerNames, String customerIdNo, String customerPhoneNumber, int seller_Id, int id) {
        this.customerNames = customerNames;
        this.customerIdNo = customerIdNo;
        this.customerPhoneNumber = customerPhoneNumber;
        this.seller_Id = seller_Id;
        this.id = id;
    }

    private String customerNames;
    private String customerIdNo;
    private String customerPhoneNumber;
    private int seller_Id;
    private int id;

    public String getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(String customerNames) {
        this.customerNames = customerNames;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getSeller_Id() {
        return seller_Id;
    }

    public void setSeller_Id(int seller_Id) {
        this.seller_Id = seller_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
