package com.wsv.right_light_wsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {

    private List<Customer> mCustomers;
    private Context mContext;

    public CustomerListAdapter(List<Customer> mCustomers, Context mContext) {
        this.mCustomers = mCustomers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerlistitem,parent,false);

        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
