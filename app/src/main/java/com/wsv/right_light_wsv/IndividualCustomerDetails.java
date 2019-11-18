package com.wsv.right_light_wsv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.parceler.Parcels;

import java.util.List;

public class IndividualCustomerDetails extends AppCompatActivity {

    private ViewPager mViewPager;
    private CustomerPageAdapter adapterViewPagerCustomer;
    private List<Customer> mCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individualcustomerdetails);

        mViewPager =findViewById(R.id.viewPager);

        mCustomers = Parcels.unwrap(getIntent().getParcelableExtra("customers"));

        int startPosition = getIntent().getIntExtra("position",0);

        System.out.println("here is another stupid error in the activity:" + mCustomers);

        adapterViewPagerCustomer = new CustomerPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mCustomers);
        mViewPager.setAdapter(adapterViewPagerCustomer);
        mViewPager.setCurrentItem(startPosition);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editcustomerdetails,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.editIcon :
                
        }
        return super.onOptionsItemSelected(item);
    }
}
