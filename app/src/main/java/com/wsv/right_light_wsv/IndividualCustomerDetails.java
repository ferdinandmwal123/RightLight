package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

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
}
