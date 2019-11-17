package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomersActivity extends AppCompatActivity implements View.OnClickListener{
    //    String [] customerNameList ={"John Doe","Louis Otieno","Robin Mwaura","Ferdinard Thiog'o","Julia Mwong'ina","Kimatu Franklin"};


    private FloatingActionButton mAddCustomerFloatingBtn;
    RecyclerView mRecyclerView;
    TextView errorTextView,testCustomerName;
    ProgressBar mProgressBar;

    public List<Customer> mCustomers;
    CustomerListAdapter adapter;
    CustomerNamesResponse customerNamesResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);


        mAddCustomerFloatingBtn = findViewById(R.id.addCustomerFloatingBtn);
        mAddCustomerFloatingBtn.setOnClickListener(this);

        errorTextView = findViewById(R.id.errorTextView);
        mProgressBar =findViewById(R.id.progress_bar);
        testCustomerName =findViewById(R.id.testCustomerName);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://rightlight.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         customerNamesResponse = retrofit.create(CustomerNamesResponse.class);

       // addCustomer();//posting method

        Call<List<Customer>> call = customerNamesResponse.getCustomerNames();

        Log.i("Here",call.toString());

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {


              if (response.isSuccessful()){
                  mCustomers= response.body();
                  Collections.sort(mCustomers,Customer.SORT_BY_NAME);


                  mRecyclerView = findViewById(R.id.customerRecyclerView);
                  mRecyclerView.setLayoutManager(new LinearLayoutManager(CustomersActivity.this));
                  mRecyclerView.setHasFixedSize(true);

                  DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(CustomersActivity.this,DividerItemDecoration.VERTICAL);
                  dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_line_divider));
                  mRecyclerView.addItemDecoration(dividerItemDecoration);

                  runOnUiThread(new Runnable() {
                      public void run() {
                          adapter = new CustomerListAdapter(mCustomers,CustomersActivity.this);
                          adapter.notifyDataSetChanged();
                          mRecyclerView.setAdapter(adapter);
                      }
                  });

                  hideProgressBar();
              }else{
                  hideProgressBar();
                  showFailureMessage();
              }


            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                errorTextView.setText(t.getMessage());
            }
        });


    }

//    private void addCustomer() {
//        Customer customer = new Customer("Name","12345",1,"3456789");
//
//        Call<List<Customer>> call = customerNamesResponse.addCustomer(customer);
//
//
//        call.enqueue(new Callback<List<Customer>>() {
//            @Override
//            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
//
//                if (response.isSuccessful()){
//                    final List<Customer> addDummyCustomer = response.body();
//
//                    Log.i("Adding a customer",addDummyCustomer.toString());
//                    System.out.println("Adding a customer :" +addDummyCustomer);
//
//                    mRecyclerView = findViewById(R.id.customerRecyclerView);
//                    mRecyclerView.setLayoutManager(new LinearLayoutManager(CustomersActivity.this));
//                    mRecyclerView.setHasFixedSize(true);
//
//                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(CustomersActivity.this,DividerItemDecoration.VERTICAL);
//                    dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_line_divider));
//                    mRecyclerView.addItemDecoration(dividerItemDecoration);
//
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            adapter = new CustomerListAdapter(addDummyCustomer,CustomersActivity.this);
//                            adapter.notifyDataSetChanged();
//                            mRecyclerView.setAdapter(adapter);
//                        }
//                    });
//
//                }else{
//                    hideProgressBar();
//                    showFailureMessage();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Customer>> call, Throwable t) {
//                errorTextView.setText(t.getMessage());
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.customersearchmenu,menu);

        final MenuItem searchItem = menu.findItem(R.id.customerSearch);
        final SearchView  searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }



    @Override
    public void onClick(View v) {
        if (v== mAddCustomerFloatingBtn){
            FragmentManager fragmentManager = getSupportFragmentManager();
            AddCustomerFragment  addCustomerFragment = new AddCustomerFragment();
            addCustomerFragment.show(fragmentManager,"Add a customer fragment");
        }

    }

    public void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    public void showFailureMessage(){
        errorTextView.setText("Something wen't wrong.Please check your internet connection");
        errorTextView.setVisibility(View.VISIBLE);
    }
}
