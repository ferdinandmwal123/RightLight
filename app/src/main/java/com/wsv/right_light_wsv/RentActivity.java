package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class RentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAddCustomer, mPickDate;
    private EditText mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        mAddCustomer = findViewById(R.id.btnAddCustomer);
        mDate = findViewById(R.id.etReturnDate);
        mDate.setText(date);
        mPickDate = findViewById(R.id.btnPickDate);
        mAddCustomer.setOnClickListener(this);
        mPickDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddCustomer) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            AddCustomerFragment addCustomerFragment = new AddCustomerFragment();
            addCustomerFragment.show(fragmentManager, "Add a customer fragment");

        } else if (v == mPickDate) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            PickDate pickDate = new PickDate();
            pickDate.show(fragmentManager, "Add select date");
        }
    }
}
