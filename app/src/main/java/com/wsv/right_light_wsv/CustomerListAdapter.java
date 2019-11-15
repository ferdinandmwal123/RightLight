package com.wsv.right_light_wsv;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.parceler.Parcels;

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

//        System.out.println("Our customers are" + mCustomers.get(1));

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerlistitem,parent,false);

        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.bindCustomer(mCustomers.get(position));
//        holder.customerNameTextView.setText(mCustomers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView customerNameTextView;
        private Context mContext;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            customerNameTextView =itemView.findViewById(R.id.customerNameInList);
        }

        public void bindCustomer(Customer customer){

            customerNameTextView.setText(customer.getName());
        }


        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext,IndividualCustomerDetails.class);
            intent.putExtra("position",itemPosition);
            intent.putExtra("customera", Parcels.wrap(mCustomers));
            mContext.startActivity(intent);

        }
    }
}
