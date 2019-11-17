package com.wsv.right_light_wsv;

public class ApiUtils {

    public static final String BASE_URL = "http://10.0.2.2:4567";

    private ApiUtils() {
    }

    public static ApiService getAPIService() {

        return RetrofitClient.getClient().create(ApiService.class);
    }
}