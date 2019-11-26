package com.wsv.right_light_wsv;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wsv.right_light_wsv.Constants.RED_LIGHT_BASE_URL;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MonthlyReportActivity extends AppCompatActivity {

    private MonthlyReportAPI monthlyReports;
    private MonthlyReports monthlyReport;
    private TextView mRecordText;
    private TextView mRevenueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report);

        mRecordText = findViewById(R.id.total_records);
        mRevenueText = findViewById(R.id.revenue_text);
        initializeRetrofit();
        callForRecords();


    }


    private void initializeRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RED_LIGHT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       monthlyReports = retrofit.create(MonthlyReportAPI.class);
    }

    private void callForRecords() {
        Call<List<MonthlyReports>> call = monthlyReports.getRecords();

        call.enqueue(new Callback<List<MonthlyReports>>() {
            @Override
            public void onResponse(Call<List<MonthlyReports>> call, Response<List<MonthlyReports>> response) {
                if(!response.isSuccessful()){

                }
                List<MonthlyReports> mR = response.body();
                int x = mR.size();
                mRecordText.setText(x);
            }

            @Override
            public void onFailure(Call<List<MonthlyReports>> call, Throwable t) {

            }
        });
    }






}