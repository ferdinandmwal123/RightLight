package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mReturn;
    private Spinner mProductId;
    private ApiService service;
    private ProgressDialog mProgressDialog;
    private List<ApiProdResponse> rentedProducts;
    private List<Integer> rentedProductIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        service = ApiUtils.getAPIService();


        service.getRentedProducts(true).enqueue(new Callback<List<ApiProdResponse>>() {
            @Override
            public void onResponse(Call<List<ApiProdResponse>> call, Response<List<ApiProdResponse>> response) {
                rentedProducts = response.body();
                getRentedProductIds(rentedProducts);

            }

            @Override
            public void onFailure(Call<List<ApiProdResponse>> call, Throwable t) {

            }
        });

        mProductId = findViewById(R.id.productSpinner);
        mReturn = findViewById(R.id.btnReturn);
        mReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mReturn){

            returnProduct();
        }
    }


    public List<Integer> getRentedProductIds(List<ApiProdResponse> rentedProducts){
        rentedProductIds = new ArrayList<>();
        mProductId = findViewById(R.id.productSpinner);
        for(ApiProdResponse product:rentedProducts)
        {

            rentedProductIds.add(product.getId());
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,rentedProductIds);
        mProductId.setAdapter(arrayAdapter);

        return rentedProductIds;

    }

    public void returnProduct() {

        mProductId = findViewById(R.id.productSpinner);
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
        service.setReturned(Integer.parseInt(mProductId.getSelectedItem().toString()),apiProdResponse).enqueue(new Callback<ApiProdResponse>() {
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
