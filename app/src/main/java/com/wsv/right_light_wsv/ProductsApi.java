package com.wsv.right_light_wsv;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApi {

    @GET("api/product/")
    Call<ProductsResponse> getAllProducts(


    );
}
