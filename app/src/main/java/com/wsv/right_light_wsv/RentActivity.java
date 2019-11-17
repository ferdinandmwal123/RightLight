package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.wsv.right_light_wsv.RentRecordAPI.models.RentRecordPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    private ApiService mAPIService;
    private Button mAddCustomer, mRentProduct;
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

        mRentProduct = findViewById(R.id.btnRecordDetails);
        mAPIService = ApiUtils.getAPIService();

        mRentProduct.setOnClickListener(this);
        mAddCustomer.setOnClickListener(this);

        CalendarView calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                EditText return_date = findViewById(R.id.etReturnDate);
                return_date.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == mAddCustomer) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            AddCustomerFragment addCustomerFragment = new AddCustomerFragment();
            addCustomerFragment.show(fragmentManager, "Add a customer fragment");

        } else if (v == mRentProduct) {
            EditText product_id = findViewById(R.id.etProducts);
            EditText return_date = findViewById(R.id.etReturnDate);
            EditText customer_id = findViewById(R.id.etCustomerName);

            int product = Integer.parseInt(product_id.getText().toString());
            int customer = Integer.parseInt(customer_id.getText().toString());
            String returnDate = return_date.getText().toString();

            rentProduct(product, customer, returnDate, "16/11/2019", 20.0);

        }
    }


    public void rentProduct(int product_id, int customer_id, String return_date, String rent_date, double amount) {
        mAPIService.rentProduct(product_id, customer_id, 1, rent_date, return_date, amount).enqueue(new Callback<RentRecordPost>() {
            @Override
            public void onResponse(Call<RentRecordPost> call, Response<RentRecordPost> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(RentActivity.this, "Added", Toast.LENGTH_LONG);

                }
            }

            @Override
            public void onFailure(Call<RentRecordPost> call, Throwable t) {

                Log.e(TAG, "ERROR");
            }
        });
    }
}
