package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomersActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton mAddCustomerFloatingBtn;
    ListView mRecyclerView;
    String [] customerNameList ={"Frank Kimatu","Louis Otieno","Robin Mwaura","Ferdinard Thiog'o","Julia Mwong'ina","Kimatu Franklin"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);


        mAddCustomerFloatingBtn = findViewById(R.id.addCustomerFloatingBtn);
        mAddCustomerFloatingBtn.setOnClickListener(this);
        mRecyclerView =(ListView) findViewById(R.id.customerRecyclerView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,customerNameList);
        mRecyclerView.setAdapter(adapter);


        mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClickedPosition = (String)adapter.getItem(position);
                Intent intent = new Intent(CustomersActivity.this,IndividualCustomerDetails.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customersearchmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.customerSearch);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search customer...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
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
