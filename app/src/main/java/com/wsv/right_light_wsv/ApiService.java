package com.wsv.right_light_wsv;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/api/customer/")
    Call<List<ApiCustomerResponse>> getCustomersList();

    @PATCH("/api/product/{id}/")
    Call<ApiProdResponse> setRented(@Path("id") int id, @Body ApiProdResponse apiProdResponse);

    @PATCH("/api/product/{id}/")
    Call<ApiProdResponse> setReturned(@Path("id") int id, @Body ApiProdResponse apiProdResponse);

    @POST("/api/rent_record/")
    Call<ApiRentResponse> rentProduct(@Body ApiRentResponse apiRentResponse );

    @GET("/api/rent_record/")
    Call<List<ApiRentResponse>> getProductRentRecord(@Query("product") int product);

    @GET("/api/rent_record/")
    Call<List<ApiRentResponse>> getCustomerRentRecord(@Query("customer") int customer_id);

    @POST("/api/product/")
    Call<ApiProdResponse> addProduct(@Body ApiProdResponse apiProdResponse);


    @GET("/api/rent_record/")
    Call<List<ApiRentResponse>> getCustomerRentRecord(@Query("customer") int customer_id);


    @GET("/api/rent_record/")
    Call<List<ApiRentResponse>> getCustomerLateReturns(@Query("customer") int customer_id,@Query ("late") boolean late);

    @GET("/api/rent_record/")
    Call<List<ApiRentResponse>> getCustomerRentals(@Query("customer") int customer_id);
    @PATCH("/api/product/{id}")
    Call<ApiRentResponse> returnProduct(@Path ("id") int id,@Body ApiRentResponse apiRentResponse );

    @GET("/api/product/")
    Call<List<ApiProdResponse>> getAllProducts();

    @GET("/api/product/")
    Call<List<ApiProdResponse>> getAvailableProducts(@Query("available") boolean available);

    @GET("/api/product/")
    Call<List<ApiProdResponse>> getRentedProducts(@Query("rented") boolean rented);

}
