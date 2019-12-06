package com.wsv.right_light_wsv;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditCustomerFragment extends DialogFragment implements View.OnClickListener {

    private EditText mEditCustomerNameField,mEditCustomerPhoneNumberField,mEditCustomerIdNoField;
    private Button mEditCustomerButton;
    private final String BASE_URL ="https://rightlight.herokuapp.com/api/";
    private Customer myCustomer;
    private int customer_id;



    public EditCustomerFragment() {
        // Required empty public constructor
    }




    public EditCustomerFragment(int customer_id) {
        this.customer_id = customer_id;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        EditCustomerFragment editCustomerFragment  = new EditCustomerFragment(customer_id);



        editCustomerFragment.myCustomer =myCustomer;

        View itemView = inflater.inflate(R.layout.fragment_edit_customer, container, false);

        mEditCustomerNameField = itemView.findViewById(R.id.updateCustomerNameField);
        mEditCustomerPhoneNumberField =itemView.findViewById(R.id.updateCustomerPhoneNumberField);
        mEditCustomerIdNoField = itemView.findViewById(R.id.updateCustomerIdNoField);
        mEditCustomerButton = itemView.findViewById(R.id.editCustomerBtn);

        
        mEditCustomerButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return itemView;
    }

    @Override
    public void onClick(View v) {
        editCustomerDetails();
    }

    private void editCustomerDetails() {

        String newName =mEditCustomerNameField.getText().toString();
        String newPhoneNumber = mEditCustomerPhoneNumberField.getText().toString();
        String newIdNo = mEditCustomerIdNoField.getText().toString();
        int seller = 1;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerNamesResponse customerNamesResponse = retrofit.create(CustomerNamesResponse.class);




        Call<List<Customer>> call = customerNamesResponse.updateCustomerDetails(customer_id,newName,newPhoneNumber,seller,newIdNo);

        System.out.println("New call for update" + call);


        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_SHORT).show();

                    Toast.makeText(getContext(),"On success",Toast.LENGTH_SHORT).show();
                    dismiss();
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                dismiss();

            }
        });

    }
}
