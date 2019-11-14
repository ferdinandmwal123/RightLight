package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRentOut:

                startActivity(new Intent(ProductDetailsActivity.this, RentActivity.class));


        }
    }
}

