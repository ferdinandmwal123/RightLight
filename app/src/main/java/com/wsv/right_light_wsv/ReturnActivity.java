package com.wsv.right_light_wsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ReturnActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);

        mReturn = findViewById(R.id.btnReturn);
        mReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mReturn){
            Dialog dialog = new Dialog(ReturnActivity.this);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.alert_dialog);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            dialog.show();
        }
    }
}
