package com.wsv.right_light_wsv;

import android.os.Bundle;

<<<<<<< HEAD
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wsv.right_light_wsv.Constants.RED_LIGHT_BASE_URL;
=======
import androidx.appcompat.app.AppCompatActivity;

>>>>>>> 466f8e7ac58a7023c0737b54a3285875154a07c3

public class MonthlyReportActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RED_LIGHT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}