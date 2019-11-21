package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mReturn;
    private EditText mProductId;
    private ApiService service;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        service = ApiUtils.getAPIService();


        mProductId = findViewById(R.id.etProductId);
        mReturn = findViewById(R.id.btnReturn);
        mReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mReturn){

            returnProduct();
        }
    }

    public void returnProduct() {

        ApiRentResponse apiRentResponse= new ApiRentResponse();
        apiRentResponse.setDamaged(false);
        apiRentResponse.setLate(false);
        apiRentResponse.setReturned(true);

        ApiProdResponse apiProdResponse = new ApiProdResponse();
        apiProdResponse.setAvailable(true);
        apiProdResponse.setRented(false);


        mProgressDialog = new ProgressDialog(ReturnActivity.this);
        mProgressDialog.setMessage("Recording Details....");
        mProgressDialog.show();
        service.setReturned(3,apiProdResponse).enqueue(new Callback<ApiProdResponse>() {
            @Override
            public void onResponse(Call<ApiProdResponse> call, Response<ApiProdResponse> response) {
                mProgressDialog.dismiss();

                Dialog dialog = new Dialog(ReturnActivity.this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.alert_dialog);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();

                Intent intent = new Intent(ReturnActivity.this,HomeActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<ApiProdResponse> call, Throwable t) {

            }
        });

    }
}
