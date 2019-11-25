package com.wsv.right_light_wsv;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    private ApiService mAPIService;
    private Button mAddCustomer, mRentProduct;
    private EditText mDate;
    private Spinner mProductIdSpinner;
    private AutoCompleteTextView mCustomer;
    private ProgressDialog mProgressDialog;
    private List<ApiProdResponse> availableProducts;
    private List<ApiCustomerResponse> customers;
    private List<Integer> availableProductIds;
    private List<String> customerList;
    HashMap<String, Integer> customerIds = new HashMap<>();
    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");


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
        mAPIService.getAvailableProducts(true).enqueue(new Callback<List<ApiProdResponse>>() {
            @Override
            public void onResponse(Call<List<ApiProdResponse>> call, Response<List<ApiProdResponse>> response) {
                availableProducts = response.body();
                getAvailableProductIds(availableProducts);

            }

            @Override
            public void onFailure(Call<List<ApiProdResponse>> call, Throwable t) {

            }
        });

        mAPIService.getCustomersList().enqueue(new Callback<List<ApiCustomerResponse>>() {
            @Override
            public void onResponse(Call<List<ApiCustomerResponse>> call, Response<List<ApiCustomerResponse>> response) {
                customers = response.body();
                getCustomers(customers);

            }

            @Override
            public void onFailure(Call<List<ApiCustomerResponse>> call, Throwable t) {

                Dialog dialog = new Dialog(RentActivity.this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.alert_dialog);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();

            }
        });





        mRentProduct.setOnClickListener(this);
        mAddCustomer.setOnClickListener(this);

        CalendarView calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                EditText return_date = findViewById(R.id.etReturnDate);
                return_date.setText(year + "-" + (month+1) + "-" + dayOfMonth);
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
            Spinner product_id = findViewById(R.id.productSpinner);
            EditText return_date = findViewById(R.id.etReturnDate);
            AutoCompleteTextView customer_id = findViewById(R.id.autoCustomer);
            EditText cost = findViewById(R.id.etCost);

            int product = Integer.parseInt(product_id.getSelectedItem().toString());
            int customer = (customerIds.get(customer_id.getText().toString()));
            String returnDate = return_date.getText().toString();
            int prod_cost = Integer.parseInt(cost.getText().toString());

            getRentDays(returnDate);

            final ApiRentResponse apiRentResponse = new ApiRentResponse();
            apiRentResponse.setCustomer(customer);
            apiRentResponse.setProduct(product);
            apiRentResponse.setReturnDate(returnDate);
            apiRentResponse.setDamaged(false);
            apiRentResponse.setReturned(false);
            apiRentResponse.setLate(false);
            apiRentResponse.setRentDate(getCurrentDate());
            apiRentResponse.setCost(prod_cost);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(RentActivity.this);
            builder1.setTitle("Confirm Details");
            builder1.setMessage("Product ID:\t\t\t"+product+"\nCustomer:\t\t\t"+customer_id.getText().toString()+"\nReturn Date:\t\t\t"+returnDate+"\nTotal Cost:\t\t\t"+prod_cost);
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


    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String strDate = mdformat.format(calendar.getTime());
        System.out.println(strDate);
        return strDate.trim();
    }

    public List<Integer> getAvailableProductIds(List<ApiProdResponse> availableProducts){
        availableProductIds = new ArrayList<>();
        mProductIdSpinner = findViewById(R.id.productSpinner);
        for(ApiProdResponse product:availableProducts)
        {

            availableProductIds.add(product.getId());
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,availableProductIds);
        mProductIdSpinner.setAdapter(arrayAdapter);

        return availableProductIds;

    }

    public long getRentDays(String returnDate) {
        long days = 1;

        String rentDate = getCurrentDate();
        try {
            Date date1 = mdformat.parse(rentDate);
            Date date2 = mdformat.parse(returnDate);
            long diff = date2.getTime() - date1.getTime();
            days = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        }catch (ParseException e) {
            e.printStackTrace();
        }


        return days;
    }

    public List<String> getCustomers(List<ApiCustomerResponse> customers){

        customerList = new ArrayList<>();

        mCustomer = findViewById(R.id.autoCustomer);
        for(ApiCustomerResponse customer:customers)
        {
            customerIds.put(customer.getName(),customer.getId());
            customerList.add(customer.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,customerList);
        mCustomer.setAdapter(arrayAdapter);

        return customerList;
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