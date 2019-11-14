package com.wsv.right_light_wsv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PickDate extends DialogFragment implements View.OnClickListener {


    private CalendarView mCalendarView;
    private EditText etDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popup_calendar_view, container, false);
        getDialog().setTitle("Pick Date");


        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
