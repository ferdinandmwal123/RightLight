package com.wsv.right_light_wsv;

import com.wsv.right_light_wsv.RentRecordAPI.models.RentRecordPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/product")
    @FormUrlEncoded
    Call<RentRecordPost> rentProduct(@Field("product_id") int product_id,
                                     @Field("customer_id") int customer_id,
                                     @Field("seller_id") int seller_id,
                                     @Field("rent_date") String rent_date,
                                     @Field("return_date") String return_date,
                                     @Field("amount") double amount);


    @POST("/api/product/")
    //Call<ApiProdResponse> addProduct(@Body ApiProdResponse apiProdResponse);
    @FormUrlEncoded
    Call<ApiProdResponse> addProduct(@Field("product_id") String product_id,
                                     @Field("product_category") String product_category,
                                     @Field("product_type") String product_type,
                                     @Field("seller") int seller,
                                     @Field("rented") boolean rented,
                                     @Field("available") boolean available,
                                     @Field("damaged") boolean damaged);

    @GET("/api/product/")
    Call<List<ApiProdResponse>> getAllProducts();

    @GET("/api/product/")
    Call<List<ApiProdResponse>> getAvailableProducts(@Query("available") boolean available);

    @GET("/api/product/")
    Call<List<ApiProdResponse>> getRentedProducts(@Query("rented") boolean rented);

}
