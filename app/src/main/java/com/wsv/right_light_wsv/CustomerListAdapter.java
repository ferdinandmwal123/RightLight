package com.wsv.right_light_wsv;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;
public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> implements Filterable {
    private List<Customer> mCustomers;
    private Context mContext;
    private List<Customer> customers;
    public CustomerListAdapter(List<Customer> customers, Context context) {
        this.mCustomers = customers;
        this.mContext = context;
        this.customers =customers;
    }
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        System.out.println("Our customers are" + mCustomers.get(1));
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerlistitem,parent,false);
        return new CustomerViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, final int position) {
        holder.bindCustomer(mCustomers.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,IndividualCustomerDetails.class);
                intent.putExtra("position",position);
                intent.putExtra("customers", Parcels.wrap(mCustomers));
                mContext.startActivity(intent);
            }
        });
//        holder.customerNameTextView.setText(mCustomers.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return mCustomers.size();
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        private TextView customerNameTextView;
        private Context mContext;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            customerNameTextView =itemView.findViewById(R.id.customerNameInList);
        }
        public void bindCustomer(Customer customer){
            customerNameTextView.setText(customer.getName());
        }
    }
    //search functionality on recyclerview
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mCustomers = customers;
                }else{
                    List<Customer> filteredCustomers = new ArrayList<>();
                    for (Customer cust : customers) {
                        if (cust.getName().toLowerCase().contains(charString.toLowerCase())){
                            filteredCustomers.add(cust);
                        }
                    }
                    mCustomers= filteredCustomers;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values =mCustomers;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCustomers =(ArrayList<Customer>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
