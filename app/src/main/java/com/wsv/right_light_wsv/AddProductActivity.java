package com.wsv.right_light_wsv;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    int seller = 1;
    private ApiProdResponse apiProdResponse;
    private ApiService mAPIService;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        type = findViewById(R.id.typeDropdown);
        category = findViewById(R.id.categoryDropdown);
        productID = findViewById(R.id.etProductId);
        addProductButton = findViewById(R.id.btnAdd);

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

            addProduct(category.getSelectedItem().toString(),type.getSelectedItem().toString(),seller,productID.getText().toString());

            mProgressDialog = new ProgressDialog(AddProductActivity.this);
            mProgressDialog.setMessage("Recording Details....");
            mProgressDialog.show();

        }

    }

    public void addProduct(String category, String type, int seller, String id) {
        ApiProdResponse apiProdResponse = new ApiProdResponse(category,type,seller,"test",false,true,false,id);
        mAPIService.addProduct(apiProdResponse).enqueue(new Callback<ApiProdResponse>() {
            @Override
            public void onResponse(Call<ApiProdResponse> call, Response<ApiProdResponse> response) {

                if (response.isSuccessful()) {
                    mProgressDialog.dismiss();

                    Dialog dialog = new Dialog(AddProductActivity.this);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.alert_dialog);
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    dialog.show();
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
