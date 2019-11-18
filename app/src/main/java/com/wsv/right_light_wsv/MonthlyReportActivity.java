package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wsv.right_light_wsv.Constants.RED_LIGHT_BASE_URL;

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
