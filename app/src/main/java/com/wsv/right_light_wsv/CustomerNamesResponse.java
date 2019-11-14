package com.wsv.right_light_wsv;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CustomerNamesResponse {

    @GET("customer")
    Call<List<Customer>> getCustomerNames();
}