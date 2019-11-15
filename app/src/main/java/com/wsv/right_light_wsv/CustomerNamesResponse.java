package com.wsv.right_light_wsv;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CustomerNamesResponse {

    @GET("customer")
    Call<List<Customer>> getCustomerNames();

    @POST("customer")
    Call<Customer> addCustomer(@Body Customer customer);
}
