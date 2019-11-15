package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class RentedProductsFragment extends Fragment {


    @Nullable
    @Override



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_rented_products, container, false);


        Button details = view.findViewById(R.id.btnRentRecordDetails);
        Button returnProduct = view.findViewById(R.id.btnReturnProduct);
        returnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ReturnActivity.class));
            }
        });
 /*      // details.setOnClickListener(new View.OnClickListener() {
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
}
