package com.wsv.right_light_wsv;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {


    private Button addProductButton;
    private EditText productID;
    private Spinner category;
    private Spinner type;
    ProgressDialog progressDialog;
    private ArrayAdapter categoryAdapter;
    private ArrayAdapter typeAdapter;
    List<String> product_category = new ArrayList<String>();
    String product_type = "Boom";
    int seller = 1;
    String product_id = "1";
    private ApiProdResponse apiProdResponse;
    private ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        type = findViewById(R.id.typeDropdown);
        category = findViewById(R.id.categoryDropdown);
        productID = findViewById(R.id.etProductId);
        addProductButton = findViewById(R.id.btnAdd);
        product_category.add("Lamp");
        // add items to category dropdown list
        category.setAdapter(categoryAdapter);
        categoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.spinner);
        categoryAdapter.notifyDataSetChanged();
        category.setAdapter(categoryAdapter);

        // add items to type dropdown list
        type.setAdapter(typeAdapter);
        typeAdapter = ArrayAdapter.createFromResource(this, R.array.lampTypes, R.layout.spinner);
        typeAdapter.notifyDataSetChanged();
        type.setAdapter(typeAdapter);

        mAPIService = RetrofitClient.getClient().create(ApiService.class);
        addProductButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == addProductButton) {

            addProduct();

        }

    }

    public void addProduct() {
        ApiProdResponse apiProdResponse = new ApiProdResponse(product_category,product_type,seller,"test",false,false,false,"B1");
        mAPIService.addProduct(apiProdResponse).enqueue(new Callback<ApiProdResponse>() {
            @Override
            public void onResponse(Call<ApiProdResponse> call, Response<ApiProdResponse> response) {

                if (response.isSuccessful()) {
                    response.code();
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    Toast.makeText(AddProductActivity.this, "Addeddddddd", LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<ApiProdResponse> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "FAilllededdddddd", LENGTH_LONG);
                Log.e(TAG, "Unable to submit post to API.", t);

            }
        });
    }

}
