package com.wsv.right_light_wsv;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCustomerFragment extends DialogFragment implements View.OnClickListener {


    private Button mAddCustomerBtn;
    private EditText mCreateCustomerNameField,mCreatePhoneNumberField,mCreateCustomerIdNoField;
    private final String BASE_URL ="https://rightlight.herokuapp.com/api/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView =inflater.inflate(R.layout.addcustomerfragment,container,false);
        getDialog().setTitle("Add customer dialog");

        mCreateCustomerNameField = rootView.findViewById(R.id.createCustomerNameField);
        mCreatePhoneNumberField = rootView.findViewById(R.id.createCustomerPhoneNumberField);
        mCreateCustomerIdNoField = rootView.findViewById(R.id.createCustomerIdNoField);

        mAddCustomerBtn =(Button) rootView.findViewById(R.id.addCustomerBtn);
        mAddCustomerBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v ==mAddCustomerBtn) {

            addCustomer();
            dismiss();
        }
    }

    private void addCustomer() {

        String customerName = mCreateCustomerNameField.getText().toString().trim();
        String customerPhoneNumber =mCreatePhoneNumberField.getText().toString().trim();
        String customerIDNo = mCreateCustomerIdNoField.getText().toString().trim();
        int seller = 1;

        if (customerName.isEmpty()){
            mCreateCustomerNameField.setError("Customer name is required");
            mCreateCustomerNameField.requestFocus();
        }
        if (customerPhoneNumber.isEmpty()){
            mCreatePhoneNumberField.setError("Phone number is required");
            mCreatePhoneNumberField.requestFocus();
        }
        if (customerIDNo.isEmpty()){
            mCreateCustomerIdNoField.setError("ID no. is required");
            mCreateCustomerIdNoField.requestFocus();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerNamesResponse customerNamesResponse = retrofit.create(CustomerNamesResponse.class);

        Call<List<Customer>> call = customerNamesResponse.addNewCustomer(customerName,customerPhoneNumber,seller,customerIDNo);

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {


                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    List<Customer> addThisCustomer =  response.body();

                    if (response.isSuccessful()){
                        Toast.makeText(getContext(), addThisCustomer.toString(), Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
