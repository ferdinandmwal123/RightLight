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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.parceler.Parcels;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndividualCustomerDetailFragment extends Fragment {

    private TextView mCustomerIdNoNumber;
    private TextView mCustomerPhoneNoNumber;

    private Customer myCustomer;

    private List<Customer> mCustomer;


    public IndividualCustomerDetailFragment() {
        // Required empty public constructor
    }

    public static IndividualCustomerDetailFragment newInstance(Customer customer) {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_individual_customer_detail, container, false);


        mCustomerNameDetails = itemView.findViewById(R.id.customersNameDetails);
       


        mCustomerNameDetails.setText("this is name" + mCustomer.toString());

        mCustomerIdNoNumber =itemView.findViewById(R.id.customersIdNoNumber);
        mCustomerPhoneNoNumber = itemView.findViewById(R.id.customersPhoneNoNumber);


        mCustomerPhoneNoNumber.setText(myCustomer.getPhoneNumber());
        mCustomerIdNoNumber.setText(myCustomer.getCustomerId());
        return itemView;
    }

}
