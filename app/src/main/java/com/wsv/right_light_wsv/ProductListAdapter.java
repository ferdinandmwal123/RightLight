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



public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.CustomViewHolder> {

    private List<ApiProdResponse> productList;
    private Context context;

    public ProductListAdapter(Context context, List<ApiProdResponse> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.product_name.setText(productList.get(position).getProductType() + " - " + productList.get(position).getProductId());
        holder.details.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("product_name", productList.get(position).getProductType()+" "+ productList.get(position).getProductCategory()+ " - " + productList.get(position).getProductId());
                    context.startActivity(intent);

            }
        });

        holder.rent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context, RentActivity.class);
                intent.putExtra("product_name", productList.get(position).getProductType()+" "+ productList.get(position).getProductCategory()+ " - " + productList.get(position).getProductId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView product_name;
        Button details, rent;
        LinearLayout parentLayout;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            product_name = mView.findViewById(R.id.txtProductName);
            details = mView.findViewById(R.id.btnProductDetails);
            parentLayout = mView.findViewById(R.id.parent_layout);
            rent = mView.findViewById(R.id.btnRent);
        }
    }

}