package com.example;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private DatabaseOpenHelper helper;

    public DAO(Context context){
        helper = new DatabaseOpenHelper(context);
    }

    public void insert(Person person){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "insert into person (id, name, age, gender) values (?,?,?,?)";
        Object[] feilds = {person.getId(),person.getName(),person.getAge(),person.getGender()};
        db.execSQL(sql,feilds);
        db.close();
    }

    public List<Person> query(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from person";
        Cursor cursor = db.rawQuery(sql,null);
        List<Person> personList = new ArrayList<>();
        while(cursor.moveToNext()){
            Integer id = cursor.getInt(0);
            String name = cursor.getString(1);
            Integer age = cursor.getInt(2);
            String gender = cursor.getString(3);
            Person person = new Person(id,name,gender,age);
            personList.add(person);
        }
        cursor.close();
        db.close();
        return personList;
    }

    public void update(Person person){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "update person set name=?,age=?,gender=? where id=?";
        Object[] feilds = {person.getName(),person.getAge(),person.getGender(),person.getId()};
        db.execSQL(sql,feilds);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "delete from person where id = ?";
        db.execSQL(sql,new Object[]{id});
        db.close();
    }

    public int getPersonNumber(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from person";
        Cursor cursor = db.rawQuery(sql,null);
        int number = cursor.getCount();
        cursor.close();
        db.close();
        return number;
    }
}
