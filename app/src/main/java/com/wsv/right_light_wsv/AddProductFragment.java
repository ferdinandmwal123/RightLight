package com.wsv.right_light_wsv;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddProductFragment extends DialogFragment implements View.OnClickListener {


    ProgressDialog progressDialog;
    String product_category = "Lamp";
    String product_type = "Boom";
    int seller = 1;
    String product_id = "1";
    private Button mAddProduct;
    private ApiService mAPIService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popup_add_product, container, false);
        getDialog().setTitle("Add customer dialog");

        mAddProduct = rootView.findViewById(R.id.btnAdd);
        mAddProduct.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == mAddProduct) {
            mAPIService = RetrofitClient.getClient().create(ApiService.class);
          /* // Call<ApiProdResponse> call = mAPIService.addProduct(product_id,product_category,product_type,seller,false,true,false);
            call.enqueue(new Callback<ApiProdResponse>() {
                @Override
                public void onResponse(Call<ApiProdResponse> call, Response<ApiProdResponse> response) {
                    dismiss();
                    Toast.makeText(getActivity(),"Added",Toast.LENGTH_LONG);
                }

                @Override
                public void onFailure(Call<ApiProdResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    TextView connectionError = getView().findViewById(R.id.txtConnectionError);
                    connectionError.setVisibility(View.VISIBLE);
                    Log.e(TAG, "onFailure: ",t );

                }
            });*/

        }
    }

}