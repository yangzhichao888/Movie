package com.example.yzc.movie;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzc.movie.main.frame;

/**
 * Created by yzc on 2017/4/14.
 */

public class login extends Activity {

    private Button btnLogin;
    private TextView tv_register;
    private EditText et_username,et_passwd;
    private DatabaseHelper  helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        helper = new DatabaseHelper (login.this,"movie.db");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        tv_register = (TextView) findViewById(R.id.register);
        et_username = (EditText) findViewById(R.id.username_edit);
        et_passwd = (EditText) findViewById(R.id.password_edit);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户名框和密码框的值
                String str1 = et_username.getText().toString();
                String str2 = et_passwd.getText().toString();
                        if (isLogin(str1,str2)){
                            Intent intent = new Intent(login.this, frame.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(login.this, "账号或者密码输入错误", Toast.LENGTH_SHORT).show();
                        }
                    }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
    public boolean isLogin(String username,String passwd){
        //////////SQLite数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from user where username=? and passwd=?";

        Cursor c = db.rawQuery(sql,new String[]{username,passwd});
        if (c.moveToFirst()){
            c.close();
            db.close();
            return true;
        }
        db.close();
        return true;
        //////////SQLite数据库
    }

}
