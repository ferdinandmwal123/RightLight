package com.wsv.right_light_wsv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RentRecordDetailsPopup extends DialogFragment implements View.OnClickListener {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView =inflater.inflate(R.layout.popup_rent_details,container,false);
        getDialog().setTitle("Add customer dialog");



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
