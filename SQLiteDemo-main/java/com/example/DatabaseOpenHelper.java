package com.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, "demo.db", null, 1);
    }

    //创建数据库时的回调函数
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("create database!");
        //创建字段
        //create table person (id integer, name varchar(32),age integer, gender varchar(8))
        String sql = "create table person (id integer, name varchar(32),age integer, gender varchar(8))";
        sqLiteDatabase.execSQL(sql);
    }

    //升级数据库时的回调函数,修改version值时进行调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        System.out.println("update database!");
    }
}
