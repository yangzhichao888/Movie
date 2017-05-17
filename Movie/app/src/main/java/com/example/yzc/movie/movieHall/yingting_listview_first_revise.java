package com.example.yzc.movie.movieHall;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzc.movie.R;

/**
 * Created by yzc on 2017/4/16.
 */

public class yingting_listview_first_revise extends Activity{

    private TextView time_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yingting_listview_first_revise);

        time_tv = (TextView) findViewById(R.id.movie_time_tv);

        time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                Toast.makeText(yingting_listview_first_revise.this, "日期时间选择器", Toast.LENGTH_SHORT).show();

//                LayoutInflater inflater = LayoutInflater.from(yingting_listview_first_revise.this);
//                View view = inflater.inflate(R.layout.dialogtimedata,null);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(yingting_listview_first_revise.this);
//                builder.setTitle((CharSequence) view);
//                AlertDialog dialog = builder.create();
//                dialog.show();

            }
        });
    }
}
