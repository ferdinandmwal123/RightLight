package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread background = new Thread(){
            public void run(){
                try {
                    sleep(5 * 1000);

                    Intent i = new Intent(getBaseContext(),HomeActivity.class);
                    startActivity(i);

                    finish();

                } catch (Exception e){
                    Toast.makeText(SplashScreenActivity.this,"Uknown error",Toast.LENGTH_LONG).show();

                }
            }
        };
    }
}
