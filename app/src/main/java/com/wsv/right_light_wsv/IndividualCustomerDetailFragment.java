package com.wsv.right_light_wsv;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndividualCustomerDetailFragment extends Fragment {
    private TextView mCustomerIdNoNumber,mCustomerPhoneNoNumber,mCustomerLateReturnNumber,mCustomerRentalsNumber,mCustomerNameDetails;

    private TextView mCustomerLastRentalDate;
    private Customer myCustomer;
    private Product myProduct;
    private List<Customer> mCustomer;
    private  ApiService apiService;


    public IndividualCustomerDetailFragment() {
        // Required empty public constructor
    }
    public static IndividualCustomerDetailFragment newInstance(Customer customer){
        IndividualCustomerDetailFragment individualCustomerDetailFragment = new IndividualCustomerDetailFragment();

        individualCustomerDetailFragment.myCustomer = customer;

        return individualCustomerDetailFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_individual_customer_detail, container, false);
        mCustomerIdNoNumber =itemView.findViewById(R.id.customersIdNoNumber);
        mCustomerPhoneNoNumber = itemView.findViewById(R.id.customersPhoneNoNumber);
        mCustomerLastRentalDate =itemView.findViewById(R.id.customersDateDate);
        mCustomerLateReturnNumber = itemView.findViewById(R.id.customersLateReturnNumber);
        mCustomerRentalsNumber = itemView.findViewById(R.id.customersRentalsNumber);
        mCustomerNameDetails = itemView.findViewById(R.id.customerNameDetails);



        mCustomerPhoneNoNumber.setText(myCustomer.getPhoneNumber());
        mCustomerIdNoNumber.setText(myCustomer.getCustomerId());
        mCustomerNameDetails.setText(myCustomer.getName());
        return itemView;
    }

    public void getLastRentRecord(List<ApiRentResponse> customerRentRecordHistory){
        int lastRecord =customerRentRecordHistory.size() -1;
        String lastDate = customerRentRecordHistory.get(lastRecord).getRentDate();
        mCustomerLastRentalDate.setText(lastDate);
    }

    public void getCountOfLateReturns(List<ApiRentResponse> customerCountOfLateReturn){



        mCustomerLateReturnNumber.setText(String.valueOf(customerCountOfLateReturn.size()));
    }

    public void getCustomerRentals(List<ApiRentResponse> customerRentals){


        mCustomerRentalsNumber.setText(String.valueOf(customerRentals.size()));

    }

    private void makeApiCall() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ApiRentResponse>> call = apiService.getCustomerRentRecord(myCustomer.getId());

        call.enqueue(new Callback<List<ApiRentResponse>>() {
            @Override
            public void onResponse(Call<List<ApiRentResponse>> call, Response<List<ApiRentResponse>> response) {

                getLastRentRecord(response.body());
                getCustomerRentals(response.body());


            }

            @Override
            public void onFailure(Call<List<ApiRentResponse>> call, Throwable t) {

            }
        });

        Call<List<ApiRentResponse>> call1 = apiService.getCustomerLateReturns(myCustomer.getId(),true);

        call1.enqueue(new Callback<List<ApiRentResponse>>() {
            @Override
            public void onResponse(Call<List<ApiRentResponse>> call, Response<List<ApiRentResponse>> response) {

                getCountOfLateReturns(response.body());

            }

            @Override
            public void onFailure(Call<List<ApiRentResponse>> call, Throwable t) {

            }
        });

        Call<List<ApiRentResponse>> call2 = apiService.getCustomerRentals(myCustomer.getId());
        call2.enqueue(new Callback<List<ApiRentResponse>>() {
            @Override
            public void onResponse(Call<List<ApiRentResponse>> call, Response<List<ApiRentResponse>> response) {

                getCustomerRentals(response.body());
            }

            @Override
            public void onFailure(Call<List<ApiRentResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        makeApiCall();
        Toast.makeText(getActivity(), "Fetching details", Toast.LENGTH_SHORT).show();
    }
}