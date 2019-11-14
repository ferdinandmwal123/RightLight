package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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


    public List<Customer> mCustomers;
    TextView errorTextView;
    CustomerListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);


        mAddCustomerFloatingBtn = findViewById(R.id.addCustomerFloatingBtn);
        mAddCustomerFloatingBtn.setOnClickListener(this);

        errorTextView = findViewById(R.id.errorTextView);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://rightlight.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerNamesResponse customerNamesResponse = retrofit.create(CustomerNamesResponse.class);

        Call<List<Customer>> call = customerNamesResponse.getCustomerNames();

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

              if (response.isSuccessful()){
                  mCustomers= response.body();

                  mRecyclerView =(RecyclerView) findViewById(R.id.customerRecyclerView);
                  mRecyclerView.setLayoutManager(new LinearLayoutManager(CustomersActivity.this));
                  mRecyclerView.setHasFixedSize(true);

                  adapter = new CustomerListAdapter(mCustomers,CustomersActivity.this);
                  mRecyclerView.setAdapter(adapter);

              }

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                errorTextView.setText(t.getMessage());
            }
        });






      //        mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String itemClickedPosition = (String)adapter.getItem(position);
//                Intent intent = new Intent(CustomersActivity.this,IndividualCustomerDetails.class);
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.customersearchmenu,menu);

        final MenuItem searchItem = menu.findItem(R.id.customerSearch);
        final SearchView  searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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
}
