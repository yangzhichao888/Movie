package com.example.yzc.movie.employee;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.yzc.movie.DatabaseHelper;
import com.example.yzc.movie.R;


/**
 * Created by yzc on 2017/5/6.
 */

public class employee_add extends Activity {

    private EditText et_employee_name,et_employee_sex,et_employee_tel,et_employee_date;
    private ImageView iv_employee;
    private Button bt_employee_add,bt_employee_cancel;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_add);

//        iv_employee = (ImageView) findViewById(R.id.employee_image);
        et_employee_name = (EditText) findViewById(R.id.employee_name_edit);
        et_employee_sex = (EditText) findViewById(R.id.employee_sex_edit);
//        et_employee_date = (EditText) findViewById(R.id.employee_joindate_edit);
        et_employee_tel = (EditText) findViewById(R.id.employee_tel_edit);
        bt_employee_add = (Button) findViewById(R.id.bt_employee_add);
        bt_employee_cancel = (Button) findViewById(R.id.bt_employee_cancel);

        helper = new DatabaseHelper(employee_add.this,"movie.db");
        final SQLiteDatabase db = helper.getWritableDatabase();
        final ContentValues values = new ContentValues();

        bt_employee_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(employee_add.this,employee.class);
                startActivity(intent);
            }
        });
        bt_employee_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String employee_name = et_employee_name.getText().toString();
                String employee_sex = et_employee_sex.getText().toString();
                String employee_tel = et_employee_tel.getText().toString();
                values.put("employee_name",employee_name);
                values.put("employee_sex",employee_sex);
                values.put("employee_tel",employee_tel);
                db.insert("employee",null,values);
                values.clear();

                et_employee_name.setText("");
                et_employee_sex.setText("");
                et_employee_tel.setText("");
                Toast.makeText(employee_add.this,"员工添加成功", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
