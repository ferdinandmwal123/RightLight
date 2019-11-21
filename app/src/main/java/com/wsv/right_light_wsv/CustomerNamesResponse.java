package com.wsv.right_light_wsv;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerNamesResponse {

    @GET("customer")
    Call<List<Customer>> getCustomerNames();

    @FormUrlEncoded
    @POST("customer/")
    Call<List<Customer>> addNewCustomer(
            @Field("Name") String Name,
            @Field("phone_number") String phone_number,
            @Field("seller") int seller,
            @Field("customer_id") String customer_id
    );

    //updating the user details
    @FormUrlEncoded
    @PATCH("customer/{id}/")
    Call<List<Customer>> updateCustomerDetails(
            @Path("id") int id,
            @Field("Name") String Name,
            @Field("phone_number") String phone_number,
            @Field("seller") int seller,
            @Field("customer_id") String customer_id
    );
}
