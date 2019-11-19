package com.wsv.right_light_wsv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RentRecordsAdapter extends RecyclerView.Adapter<RentRecordsAdapter.CustomViewHolder> {

    private List<ApiRentResponse> rentRecords;
    private Context context;

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

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
//        holder.customer.setText(rentRecords.get(position).getCustomer());
        holder.returnDate.setText(rentRecords.get(position).getReturnDate());
        holder.rentDate.setText(rentRecords.get(position).getRentDate());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == holder.details) {

                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    context.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return rentRecords.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView customer,rentDate,returnDate;
        Button details;
        LinearLayout parentLayout;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            rentDate = mView.findViewById(R.id.txtRentDate);
            returnDate = mView.findViewById(R.id.txtReturnDate);

            customer = mView.findViewById(R.id.testCustomerName);
            parentLayout = mView.findViewById(R.id.parent_layout);
        }
    }

}