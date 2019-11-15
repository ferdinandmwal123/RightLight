package com.wsv.right_light_wsv;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class ProductsActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_products);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        //API


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View popupView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_add_product, null);
                mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                mPopupWindow.showAtLocation(viewPager, Gravity.CENTER, 0, 0);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();
            }
        });
    }
}