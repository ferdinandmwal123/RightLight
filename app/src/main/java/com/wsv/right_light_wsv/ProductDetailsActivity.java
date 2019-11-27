package com.wsv.right_light_wsv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRentOut,mMarkAsDamaged,mDetails;
    private TextView txtProductsName,availabilityBadge;
    private RecyclerView recyclerView;
    private RentRecordsAdapter adapter;
    ProgressDialog progressDialog;
    ApiService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_details);
        mRentOut = findViewById(R.id.btnRentOut);
        mRentOut.setOnClickListener(this);
        txtProductsName = findViewById(R.id.txtProductName);
        availabilityBadge = findViewById(R.id.availabilityBadge);


        mMarkAsDamaged = findViewById(R.id.btnMarkAsDamaged);
        mMarkAsDamaged.setOnClickListener(this);
        mDetails = findViewById(R.id.btnRentRecordDetails);

        String mProductName=getIntent().getStringExtra("product_name");

        txtProductsName.setText(mProductName);

        boolean availability = getIntent().getExtras().getBoolean("availability");
        System.out.println(availability);
        int productId = getIntent().getIntExtra("product_id",1);

        String available;
        if (availability==true) {
            available="available";
            availabilityBadge.setText(available);
        }else if(!availability){
            available="not available";
            availabilityBadge.setText(available);
        }


        service = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ApiRentResponse>> call = service.getProductRentRecord(productId);

        progressDialog = new ProgressDialog(ProductDetailsActivity.this);
        progressDialog.setMessage("Loading Products Details....");
        progressDialog.show();

        call.enqueue(new Callback<List<ApiRentResponse>>() {
            @Override
            public void onResponse(Call<List<ApiRentResponse>> call, Response<List<ApiRentResponse>> response) {
                progressDialog.dismiss();
                getRentDetails(response.body());
            }

            @Override
            public void onFailure(Call<List<ApiRentResponse>> call, Throwable t) {
                progressDialog.dismiss();
                TextView connectionError = findViewById(R.id.txtConnectionError);
                connectionError.setVisibility(View.VISIBLE);
                Log.e(TAG, "onFailure: ", t);

            }
        });



    }

    @Override
    public void onClick(View v) {
        if(v == mRentOut) {
                startActivity(new Intent(ProductDetailsActivity.this, RentActivity.class));

        }else if(v==mMarkAsDamaged){

        }
        else if(v==mDetails){
            FragmentManager fragmentManager = getSupportFragmentManager();
            RentRecordDetailsPopup rentRecordDetailsPopup = new RentRecordDetailsPopup();
            rentRecordDetailsPopup.show(fragmentManager, "Add a customer fragment");

        }
    }


    private void getRentDetails(List<ApiRentResponse> rentRecords) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RentRecordsAdapter(ProductDetailsActivity.this, rentRecords);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

