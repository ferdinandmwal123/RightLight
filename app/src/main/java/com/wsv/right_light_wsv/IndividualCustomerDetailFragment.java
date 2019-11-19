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
import org.parceler.Parcels;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndividualCustomerDetailFragment extends Fragment {
    private TextView mCustomerIdNoNumber,mCustomerPhoneNoNumber,mCustomerLateReturnNumber;

    private TextView mCustomerLastRentalDate;
    private Customer myCustomer;
    private List<Customer> mCustomer;
    private  ApiService apiService;


    public IndividualCustomerDetailFragment() {
        // Required empty public constructor
    }
    public static IndividualCustomerDetailFragment newInstance(Customer customer){
        IndividualCustomerDetailFragment individualCustomerDetailFragment = new IndividualCustomerDetailFragment();
//        Bundle args = new Bundle();
        individualCustomerDetailFragment.myCustomer = customer;
//        args.putParcelable("customer", Parcels.wrap(customer));
//
//        individualCustomerDetailFragment.setArguments(args);
        return individualCustomerDetailFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Customer customer = new Customer();

        apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ApiRentResponse>> call = apiService.getCustomerRentRecord(customer.getId());

        call.enqueue(new Callback<List<ApiRentResponse>>() {
            @Override
            public void onResponse(Call<List<ApiRentResponse>> call, Response<List<ApiRentResponse>> response) {

                getLastRentRecord(response.body());
                getCountOfLateReturns(response.body());


            }

            @Override
            public void onFailure(Call<List<ApiRentResponse>> call, Throwable t) {

            }
        });
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



        mCustomerPhoneNoNumber.setText(myCustomer.getPhoneNumber());
        mCustomerIdNoNumber.setText(myCustomer.getCustomerId());
        return itemView;
    }

    public void getLastRentRecord(List<ApiRentResponse> customerRentRecordHistory){
        int lastRecord =customerRentRecordHistory.size() -1;
        String lastDate = customerRentRecordHistory.get(lastRecord).getRentDate();
        mCustomerLastRentalDate.setText(lastDate);
    }

    public void getCountOfLateReturns(List<ApiRentResponse> customerCountOfLateReturn){
        int lateCount = 0;
        Customer customer = new Customer();
        int checkCustomerId = customer.getId();
        boolean checkIfLate = customerCountOfLateReturn.get(checkCustomerId).getLate();

        if (checkIfLate == false){
            lateCount = lateCount++;
        }
        mCustomerLateReturnNumber.setText(String.valueOf(lateCount));
    }
}