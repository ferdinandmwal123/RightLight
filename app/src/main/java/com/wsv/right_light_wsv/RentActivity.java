package com.wsv.right_light_wsv;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    private ApiService mAPIService;
    private Button mAddCustomer, mRentProduct;
    private EditText mDate;
    private ProgressDialog mProgressDialog;

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
                return_date.setText(year + "-" + month + "-" + dayOfMonth);
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
            EditText cost = findViewById(R.id.etCustomerName);

            int product = Integer.parseInt(product_id.getText().toString());
            int customer = Integer.parseInt(customer_id.getText().toString());
            String returnDate = return_date.getText().toString();
            int prod_cost = Integer.parseInt(cost.getText().toString());

            ApiRentResponse apiRentResponse = new ApiRentResponse();
            apiRentResponse.setCustomer(customer);
            apiRentResponse.setProduct(product);
            apiRentResponse.setReturnDate(returnDate);
            apiRentResponse.setDamaged(false);
            apiRentResponse.setReturned(false);
            apiRentResponse.setLate(false);
            apiRentResponse.setRentDate("2019-11-21");
            apiRentResponse.setCost(prod_cost);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(RentActivity.this);
            builder1.setTitle("Confirm Details");
            builder1.setMessage("Product id:\t\t"+product+"\ncustomer:\t\t"+customer+"\nreturn date:\t\t"+returnDate);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "CONFIRM",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            rentProduct(apiRentResponse,product);

                            mProgressDialog = new ProgressDialog(RentActivity.this);
                            mProgressDialog.setMessage("Recording Details....");
                            mProgressDialog.show();

                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "CANCEL",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();







        }
    }


    public void rentProduct(ApiRentResponse apiRentResponse, int product_id) {

        ApiProdResponse apiProdResponse = new ApiProdResponse();
        apiProdResponse.setRented(true);
        apiProdResponse.setAvailable(false);

        mAPIService.rentProduct(apiRentResponse).enqueue(new Callback<ApiRentResponse>() {
            @Override
            public void onResponse(Call<ApiRentResponse> call, Response<ApiRentResponse> response) {

                if (response.isSuccessful()) {
                    mAPIService.setRented(product_id,apiProdResponse).enqueue(new Callback<ApiProdResponse>() {
                        @Override
                        public void onResponse(Call<ApiProdResponse> call, Response<ApiProdResponse> response) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ApiProdResponse> call, Throwable t) {

                        }
                    });
                    Toast.makeText(RentActivity.this, "Added", Toast.LENGTH_LONG);


                    Dialog dialog = new Dialog(RentActivity.this);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.alert_dialog);
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    Intent intent = new Intent(RentActivity.this,HomeActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<ApiRentResponse> call, Throwable t) {

                Log.e(TAG, "ERROR");
            }
        });

    }
}