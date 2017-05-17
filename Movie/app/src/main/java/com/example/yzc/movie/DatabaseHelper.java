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
        sqLiteDatabase.execSQL("create table if not exists user(_id integer primary key autoincrement,username text not null,passwd text not null)");
        sqLiteDatabase.execSQL("insert into user(username,passwd)values('yzc','qwe123')");
        sqLiteDatabase.execSQL("insert into user(username,passwd)values('hp','aaa')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
