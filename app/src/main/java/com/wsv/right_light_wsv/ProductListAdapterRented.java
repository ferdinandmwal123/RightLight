package com.wsv.right_light_wsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListAdapterRented extends RecyclerView.Adapter<ProductListAdapterRented.CustomViewHolder> {

    private List<ApiProdResponse> productList;
    private Context context;

    public ProductListAdapterRented(Context context, List<ApiProdResponse> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_list_item_rented, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.number.setText(Integer.toString(position+1));
        holder.product_name.setText(productList.get(position).getProductType() + " - " + productList.get(position).getProductId());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView product_name,number;
        Button details, rent;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            number=mView.findViewById(R.id.txtListNumber);
            product_name = mView.findViewById(R.id.txtProductName);
            details = mView.findViewById(R.id.btnProductDetails);
        }
    }
}