package com.example.yzc.movie;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzc.movie.main.frame;

/**
 * Created by yzc on 2017/4/14.
 */

public class login extends Activity {

    private Button btnLogin;
    private TextView tv_forgetpw;
    private EditText et_username,et_passwd;
    private DatabaseHelper  helper;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private CheckBox checkBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        btnLogin = (Button) findViewById(R.id.btnLogin);
        tv_forgetpw = (TextView) findViewById(R.id.forgetpw);
        et_username = (EditText) findViewById(R.id.username_edit);
        et_passwd = (EditText) findViewById(R.id.password_edit);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        //实例化SharedPreferences对象 指定MODE_PRIVATE模式还有MODE_WORLD_READABLE MODE_WORLD_WRITEABLE模式
        pref = getSharedPreferences("UserInfo",MODE_PRIVATE);
        //启用edit()获取实例
        editor = pref.edit();
        //第一个参数为取到的用户名名字，第二个参数为第一个参数的默认值
        String name = pref.getString("et_username",null);
        String passwd = pref.getString("et_passwd",null);

        //如果取到的name为空（第一次启动程序或者没有点记住用户名）单选框false状态，用户名的文本框为空
        // 否则true并将取到的name写入用户名的文本框
        if (name == null){
            checkBox1.setChecked(false);
        }
        else {
            checkBox1.setChecked(true);
            //把值放进去
            et_username.setText(name);
            et_passwd.setText(passwd);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取用户名框和密码框的值
                String userName = et_username.getText().toString();
                String userPassword = et_passwd.getText().toString();

                if (isLogin(userName,userPassword)){
                    if (checkBox1.isChecked()){
                        editor.putString("et_username",userName);//这是一个键值对
                        editor.putString("et_passwd",userPassword);//这是一个键值对
                        //注意提交
                        editor.commit();
                    }else{
                        editor.remove("et_username");
                        editor.remove("et_passwd");
                        //注意提交
                        editor.commit();
                    }
                            Intent intent = new Intent(login.this, frame.class);
                            finish();
                            startActivity(intent);
                        }
                else {
                            Toast.makeText(login.this, "账号或者密码输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_forgetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, forgetpw.class);
                startActivity(intent);
            }
        });
    }
    public boolean isLogin(String username,String passwd){
        //////////SQLite数据库
        helper = new DatabaseHelper (login.this,"movie.db");
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from user where username=? and passwd=?";

        Cursor c = db.rawQuery(sql,new String[]{username,passwd});
        if (c.moveToFirst()){
            c.close();
            db.close();
            return true;
        }
        db.close();
        return false;
        //////////SQLite数据库
    }

}
