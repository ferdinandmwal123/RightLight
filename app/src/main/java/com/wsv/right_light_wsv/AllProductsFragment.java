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

public class AllProductsFragment extends Fragment {

    ProgressDialog progressDoalog;
    ApiService service;
    private ProductListAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_all_products, container, false);

        progressDoalog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDoalog.setMessage("Fetching products from database.....");
        progressDoalog.show();

        service = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ApiProdResponse>> call = service.getAllProducts();


        call.enqueue(new Callback<List<ApiProdResponse>>() {
            @Override
            public void onResponse(Call<List<ApiProdResponse>> call, Response<List<ApiProdResponse>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ApiProdResponse>> call, Throwable t) {
                progressDoalog.dismiss();
                TextView connectionError = getView().findViewById(R.id.txtConnectionError);
                connectionError.setVisibility(View.VISIBLE);
                Log.e(TAG, "onFailure: ", t);

            }
        });







/*        Button details = view.findViewById(R.id.btnRentRecordDetails);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProductDetailsActivity.class));


            }
        });
        Button rent = view.findViewById(R.id.btnRent);
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RentActivity.class));


            }
        });*/







        return view;
    }

    private void generateDataList(List<ApiProdResponse> productList) {
        recyclerView = getView().findViewById(R.id.recyclerView);
        adapter = new ProductListAdapter(getActivity(), productList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}