package com.example.pnroy.todoapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import org.apache.commons.io.FileUtils;
import android.widget.DatePicker;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import com.example.pnroy.todoapp.data.ToDoItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import android.app.Dialog;
import android.app.DialogFragment;
import android.widget.RadioButton;

import com.example.pnroy.todoapp.data.*;


/**
 * Created by pnroy on 8/22/15.
 */
public class EditItem extends Activity {

    public static TextView tvdate;
    private static String dateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_edit_item);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.blue_tick);
        String text;
        String priority;
        String date;
        final EditText txtShow=(EditText)findViewById(R.id.TVEdit);
        final Button btnSave=(Button) findViewById(R.id.btnSave);
        final Button btnDate=(Button) findViewById(R.id.date_picker_button);
        final RadioGroup prioritygrp=(RadioGroup)findViewById(R.id.priorityGroup);
        tvdate=(TextView)findViewById(R.id.date);
        //get values passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            text= null;

        } else {
            text= extras.getString("text");
            txtShow.setText(text);

        }
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass back the updated text back to the calling activity
                Intent data = new Intent();
                int selectedId = prioritygrp.getCheckedRadioButtonId();
                data.putExtra("editedText", txtShow.getText().toString());
                data.putExtra("editedPriority",((RadioButton) findViewById(selectedId)).getText());
                data.putExtra("editedDate", tvdate.getText().toString());
                Toast.makeText(getApplicationContext(), "Item Updated!", Toast.LENGTH_LONG).show();
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();

            }
        });
    }

// DialogFragment used to pick a ToDoItem deadline date

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            setDateString(year, monthOfYear, dayOfMonth);


            tvdate.setText(dateString);
        }

    }
    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {

        // Increment monthOfYear for Calendar/Date -> Time Format setting
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10)
            mon = "0" + monthOfYear;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;

        dateString = year + "-" + mon + "-" + day;
    }

}
