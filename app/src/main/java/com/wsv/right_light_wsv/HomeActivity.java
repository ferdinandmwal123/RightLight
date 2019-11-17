package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;




public class HomeActivity extends AppCompatActivity {
    private Button mProductsBtn;
    private Button mCustomerBtn;
    private Button mRentBtn;
    private Button mReturnBtn;
    private Button mMonthlyReportButton;
    private Button mSettingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mProductsBtn = findViewById(R.id.productsBtn);
        mReturnBtn = findViewById(R.id.returnBtn);
        mCustomerBtn = findViewById(R.id.customerBtn);
        mRentBtn = findViewById(R.id.rentBtn);
        mMonthlyReportButton = findViewById(R.id.monthlyReportBtn);
        mSettingsBtn = findViewById(R.id.btnSettings);

        mProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProductsActivity.class));
            }
        });

        mCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CustomersActivity.class));
            }
        });
        mRentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,RentActivity.class));
            }
        });
        mReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ReturnActivity.class));
            }
        });
        mMonthlyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MonthlyReportActivity.class));
            }
        });
    }
}
