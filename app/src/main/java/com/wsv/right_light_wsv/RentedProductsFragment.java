package com.wsv.right_light_wsv;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RentedProductsFragment extends Fragment {

    ProgressDialog progressDialog;
    ApiService service;
    private ProductListAdapterRented adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_rented_products, container, false);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        service = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ApiProdResponse>> call = service.getRentedProducts(true);

        call.enqueue(new Callback<List<ApiProdResponse>>() {
            @Override
            public void onResponse(Call<List<ApiProdResponse>> call, Response<List<ApiProdResponse>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ApiProdResponse>> call, Throwable t) {
                progressDialog.dismiss();
                TextView connectionError = getView().findViewById(R.id.txtConnectionError);
                connectionError.setVisibility(View.VISIBLE);
                Log.e(TAG, "onFailure: ", t);

            }
        });

 /*      Button details = view.findViewById(R.id.btnRentRecordDetails);
        Button returnProduct = view.findViewById(R.id.btnReturnProduct);
        returnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ReturnActivity.class));
            }
        });

         // details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = LayoutInflater.from(getApplicationContext(getActivity())).inflate(R.layout.popup_add_product, null);
                mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();
            }
        });*/

    return view;
    }

    private void generateDataList(List<ApiProdResponse> productList) {
        recyclerView = getView().findViewById(R.id.recyclerView);
        adapter = new ProductListAdapterRented(getActivity(), productList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
