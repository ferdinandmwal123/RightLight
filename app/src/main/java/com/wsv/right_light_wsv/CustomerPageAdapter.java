package com.wsv.right_light_wsv;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
public class CustomerPageAdapter extends FragmentPagerAdapter {
    private List<Customer> mCustomers;
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return IndividualCustomerDetailFragment.newInstance(mCustomers.get(position));
    }
    @Override
    public int getCount() {
        return mCustomers.size();
    }

    public CustomerPageAdapter(@NonNull FragmentManager fm, int behavior,List<Customer> customers) {
        super(fm, behavior);
        this.mCustomers=customers;
    }
}