package com.wsv.right_light_wsv;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndividualCustomerDetailFragment extends Fragment {

    private TextView mCustomerNameDetails;
    private TextView mCustomerIdNoNumber;
    private TextView mCustomerPhoneNoNumber;

    private List<Customer> mCustomer;



    public IndividualCustomerDetailFragment() {
        // Required empty public constructor
    }

    public static IndividualCustomerDetailFragment newInstance(Customer customer){
        IndividualCustomerDetailFragment individualCustomerDetailFragment = new IndividualCustomerDetailFragment();
        Bundle args = new Bundle();


        args.putParcelable("customer", Parcels.wrap(customer));

        individualCustomerDetailFragment.setArguments(args);
        return individualCustomerDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomer = Parcels.unwrap(getArguments().getParcelable("customer"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_individual_customer_detail, container, false);


        mCustomerNameDetails = itemView.findViewById(R.id.customersNameDetails);
        mCustomerIdNoNumber =itemView.findViewById(R.id.customersIdNoNumber);
        mCustomerPhoneNoNumber = itemView.findViewById(R.id.customersPhoneNoNumber);


        mCustomerNameDetails.setText("this is name"+ mCustomer.toString());
        return itemView;
    }

}
