package com.example.yzc.movie.employee;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzc.movie.DatabaseHelper;
import com.example.yzc.movie.R;

/**
 * Created by yzc on 2017/5/13.
 */

public class employee_revise extends Activity {

    private TextView employee_id;
    private EditText employee_name,employee_sex,employee_tel;
    private Button bt_employee_revise;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_revise);
        employee_id = (TextView) findViewById(R.id.employee_et_id);
        employee_name = (EditText) findViewById(R.id.employee_name_edit);
        employee_sex = (EditText) findViewById(R.id.employee_sex_edit);
        employee_tel = (EditText) findViewById(R.id.employee_tel_edit);
        bt_employee_revise = (Button) findViewById(R.id.bt_employee_revise);
        ///显示员工编号
        Bundle bundle=getIntent().getExtras();
        final String message=bundle.getString("message");
        ///显示员工编号
        ////通过编号查出数据
        helper = new DatabaseHelper(employee_revise.this,"movie.db");
        final SQLiteDatabase db = helper.getReadableDatabase();

        final Cursor c =db.rawQuery("select * from employee where _id = ?", new String[]{message});
        c.moveToFirst();
        if (c != null) {
            int id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("employee_name"));
            String sex = c.getString(c.getColumnIndex("employee_sex"));
            int tel = c.getInt(c.getColumnIndex("employee_tel"));

            employee_id.setText(String.valueOf(id));
            employee_name.setText(name);
            employee_sex.setText(sex);
            employee_tel.setText(String.valueOf(tel));
        }

        bt_employee_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new DatabaseHelper(employee_revise.this,"movie.db");
                SQLiteDatabase db = helper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("employee_name",employee_name.getText().toString());
                values.put("employee_sex",employee_sex.getText().toString());
                values.put("employee_tel",employee_tel.getText().toString());
                db.update("employee",values,"_id = ?",new String[]{message});
                values.clear();
                db.close();
                Toast.makeText(employee_revise.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });

        c.close();
        db.close();

        ////通过编号查出数据
    }
}
