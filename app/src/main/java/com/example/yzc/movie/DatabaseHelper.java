package com.example.yzc.movie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yzc on 2017/4/23.
 */

public class DatabaseHelper  extends SQLiteOpenHelper{

    public DatabaseHelper(Context context, String name) {
                    super(context, name, null, 1);
                }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists user(_id integer primary key autoincrement,username text not null,passwd text not null,phone integer not null,email text not null)");
        sqLiteDatabase.execSQL("create table if not exists employee(_id integer primary key autoincrement,employee_name text not null,employee_sex text not null,employee_tel integer not null) ");
        sqLiteDatabase.execSQL("insert into user(username,passwd)values('hp','aaa',15129050810,'492480091@qq.com')");
        sqLiteDatabase.execSQL("insert into user(username,passwd)values('yzc','aaa',2132134352,'243241214@outlook.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //每次访问数据库时调用，一般在这写添加表的代码
    }
}
