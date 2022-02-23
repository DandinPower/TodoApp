package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewThingActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.android.newthingactivities.extra.REPLY";
    private EditText mgettitle;
    private EditText mgetinfo;
    private Button msave;
    private DatePickerDialog datePickerDialog_due;
    private DatePickerDialog datePickerDialog_do;
    private Button msetdue;
    private Button msetDo;
    private String DueDate;
    private String DoDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thing);
        DueDate = getTodaysDate();
        DoDate = getTodaysDate();
        mgettitle = findViewById(R.id.gettitle);
        mgetinfo = findViewById(R.id.getinfo);
        msave = findViewById(R.id.savebutton);
        msetdue = findViewById(R.id.setdue);
        msetDo = findViewById(R.id.setdo);
        msetdue.setText(DueDate);
        msetDo.setText(DoDate);
        initDatePicker_due();
        initDatePicker_do();

    }

    public void savedata(View view) {
        String [] reply = new String[4];
        reply[0] = mgettitle.getText().toString();
        reply[1] = mgetinfo.getText().toString();
        reply[2] = DueDate;
        reply[3] = DoDate;

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }
    private void initDatePicker_due(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                msetdue.setText(date);
                DueDate = date;
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog_due = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }
    private void initDatePicker_do(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                msetDo.setText(date);
                DoDate = date;
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog_do = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String makeDateString(int day,int month,int year){
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month){
        if(month == 1) return "JAN";
        if(month == 2) return "FEB";
        if(month == 3) return "MAR";
        if(month == 4) return "APR";
        if(month == 5) return "MAY";
        if(month == 6) return "JUN";
        if(month == 7) return "JUL";
        if(month == 8) return "AUG";
        if(month == 9) return "SEP";
        if(month == 10) return "OCT";
        if(month == 11) return "NOV";
        if(month == 12) return "DEC";
        return "JAN";
    }

    public void dueDatepicker(View view) {
        datePickerDialog_due.show();
    }

    public void doDatepicker(View view) {
        datePickerDialog_do.show();
    }
}