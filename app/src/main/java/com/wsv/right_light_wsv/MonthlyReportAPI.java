package com.wsv.right_light_wsv;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MonthlyReportAPI {

    @GET("/api/rent_record/")
    Call<List<MonthlyReports>> getRecords();
}
