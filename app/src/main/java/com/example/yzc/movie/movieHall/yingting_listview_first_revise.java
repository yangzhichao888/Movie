package com.example.yzc.movie.movieHall;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.yzc.movie.R;
import com.example.yzc.movie.employee.employee;
import com.example.yzc.movie.employee.employee_add;

import java.util.Calendar;

/**
 * Created by yzc on 2017/4/16.
 */

public class yingting_listview_first_revise extends Activity{

    private TextView time_tv,date_tv,movie_area_tv;
    private Button bt_revise,bt_cancel;

    private Calendar cal;//用来显示时间
    private int year,month,day,hour,minute;
    String[] list_leixing = {"大陆","港台","欧美"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yingting_listview_first_revise);

        time_tv = (TextView) findViewById(R.id.movie_time_tv);
        date_tv = (TextView) findViewById(R.id.movie_date_tv);
        movie_area_tv = (TextView) findViewById(R.id.movie_area_tv);
        bt_revise = (Button) findViewById(R.id.bt_yingpian_revise);
        bt_cancel = (Button) findViewById(R.id.bt_yingpian_cancel);
        bt_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(yingting_listview_first_revise.this, yingting_listview_first.class);
                startActivity(intent);
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(yingting_listview_first_revise.this, yingting_listview_first.class);
                startActivity(intent);
            }
        });

        cal = Calendar.getInstance();//获取日历对象
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);

        time_tv.setText(hour + ":" + minute);
        date_tv.setText(year + "-" + month + "-" + day);
        time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                show_timedate();
            }
        });
        date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_datedate();
            }
        });
        //////////////////////////////////////////////
        findViewById(R.id.movie_area_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_area();
            }
        });
    }
    private void show_area(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        builder.setSingleChoiceItems(list_leixing, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str = list_leixing[i];
                movie_area_tv.setText(str);
            }
        });
        builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void show_datedate(){
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date_tv.setText(year + "-" + (month+1) + "-" + day + " ");
            }
        },year,cal.get(Calendar.MONTH),day).show();
    }
    private void show_timedate(){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                time_tv.setText(hour + ":" + minute);
            }
        },hour,minute,true).show();

    }
}
