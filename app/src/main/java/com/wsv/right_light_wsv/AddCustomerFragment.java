package com.wsv.right_light_wsv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCustomerFragment extends DialogFragment implements View.OnClickListener {


    private Button mAddCustomerBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView =inflater.inflate(R.layout.addcustomerfragment,container,false);
        getDialog().setTitle("Add customer dialog");

        mAddCustomerBtn =(Button) rootView.findViewById(R.id.addCustomerButton);
        mAddCustomerBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v ==mAddCustomerBtn) {
            dismiss();
        }
    }
}
