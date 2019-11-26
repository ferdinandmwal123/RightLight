package com.wsv.right_light_wsv;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MonthlyReportAPI {

    @GET("/api/rent_record/")
    Call<List<MonthlyReports>> getRecords();

    @GET("/api/rent_record")
    Call<List<MonthlyReports>> getCost(@Query("cost") int cost);
}
