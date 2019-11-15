package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRentOut,mMarkAsDamaged,mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mRentOut = findViewById(R.id.btnRentOut);
        mRentOut.setOnClickListener(this);
        mMarkAsDamaged = findViewById(R.id.btnMarkAsDamaged);
        mMarkAsDamaged.setOnClickListener(this);
        mDetails = findViewById(R.id.btnRentRecordDetails);
        mDetails.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == mRentOut) {
                startActivity(new Intent(ProductDetailsActivity.this, RentActivity.class));

        }else if(v==mMarkAsDamaged){

        }
        else if(v==mDetails){
            FragmentManager fragmentManager = getSupportFragmentManager();
            RentRecordDetailsPopup rentRecordDetailsPopup = new RentRecordDetailsPopup();
            rentRecordDetailsPopup.show(fragmentManager, "Add a customer fragment");

        }
    }
}

