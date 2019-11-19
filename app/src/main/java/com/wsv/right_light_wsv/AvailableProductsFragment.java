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

public class AvailableProductsFragment extends Fragment {

    ProgressDialog progressDialog;
    ApiService service;
    private ProductListAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching products from database.....");
        progressDialog.show();

        service = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ApiProdResponse>> call = service.getAvailableProducts(true);

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

        return inflater.inflate(R.layout.tab_available_products, container, false);
    }

    private void generateDataList(List<ApiProdResponse> productList) {
        recyclerView = getView().findViewById(R.id.recyclerView);
        adapter = new ProductListAdapter(getActivity(), productList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

