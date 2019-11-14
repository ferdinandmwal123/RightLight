package com.wsv.right_light_wsv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {


    private CalendarView mCalendarView;
    private EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        mCalendarView.findViewById(R.id.calendar);
        etDate.findViewById(R.id.etReturnDate);
        mCalendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                startActivity(new Intent(CalendarActivity.this, RentActivity.class).putExtra("returnDate", date));
            }
        });
    }
}
