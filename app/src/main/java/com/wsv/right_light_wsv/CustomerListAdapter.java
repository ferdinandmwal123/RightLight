package com.wsv.right_light_wsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {

    private List<Customer> mCustomers;
    private Context mContext;

    public CustomerListAdapter(List<Customer> mCustomers, Context context) {
        this.mCustomers = mCustomers;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerlistitem,parent,false);

        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        Customer customer = mCustomers.get(position);

        if (customer != null) {
            holder.customerNameTextView.setText(customer.getCustomerNames());
        }
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        private TextView customerNameTextView;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);


            customerNameTextView =itemView.findViewById(R.id.customerNameInList);
        }
    }
}
