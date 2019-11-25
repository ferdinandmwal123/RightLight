package com.wsv.right_light_wsv;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentRecordsAdapter extends RecyclerView.Adapter<RentRecordsAdapter.CustomViewHolder> {

    private List<ApiRentResponse> rentRecords;
    private ApiService mAPIService;
    private List<ApiCustomerResponse> customers;
    private Context context;
    HashMap<Integer, String> customerNames = new HashMap<>();

    public RentRecordsAdapter(Context context, List<ApiRentResponse> rentRecords) {
        this.context = context;
        this.rentRecords = rentRecords;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_product_rent_record, parent, false);
        return new CustomViewHolder(view);
    }

    public void getCustomers(List<ApiCustomerResponse> customers) {

        for (ApiCustomerResponse customer : customers) {
            customerNames.put(customer.getId(), customer.getName());
        }

    }

    @Override

    public void onBindViewHolder(CustomViewHolder holder, int position) {
     /*   mAPIService = ApiUtils.getAPIService();
        mAPIService.getCustomersList().enqueue(new Callback<List<ApiCustomerResponse>>() {
            @Override
            public void onResponse(Call<List<ApiCustomerResponse>> call, Response<List<ApiCustomerResponse>> response) {
                customers = response.body();
                getCustomers(customers);
                System.out.println(customerNames);

            }

            @Override
            public void onFailure(Call<List<ApiCustomerResponse>> call, Throwable t) {

            }
        });

        int customerId = rentRecords.get(position).getCustomer();
        String customerName = customerNames.get(customerId);*/

        holder.customer.setText(Integer.toString(rentRecords.get(position).getCustomer()));
        holder.returnDate.setText(rentRecords.get(position).getReturnDate());
        holder.rentDate.setText(rentRecords.get(position).getRentDate());



    }

        @Override
        public int getItemCount() {
            return rentRecords.size();
        }
        class CustomViewHolder extends RecyclerView.ViewHolder {

            public final View mView;

            TextView customer, rentDate, returnDate;
            Button details;
            LinearLayout parentLayout;

            CustomViewHolder(View itemView) {
                super(itemView);
                mView = itemView;

                rentDate = mView.findViewById(R.id.txtRentDate);
                returnDate = mView.findViewById(R.id.txtReturnDate);

                customer = mView.findViewById(R.id.txtCustomerName);
                parentLayout = mView.findViewById(R.id.parent_layout);
            }
        }

    }

