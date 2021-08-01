package com.example.project.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.controls.actions.BooleanAction;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        //Database name
        super(context,"Garima.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table in database
        db.execSQL("create table users(username Text primary key,password Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Delete or drop database
//        db.execSQL("drop table if exists users");
        db.execSQL("create table if not exists users(username Text primary key,password Text)");

    }
    public Boolean insertData(String username, String password){

        //Insert Data in to the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        //Store the data
        long result = db.insert("users",null,contentValues);

        // negative if it failed to insert data in db
        if(result == -1){
            return false;
        }
        else{
            return  true;
        }
    }
    public Boolean chkUserName(String username){
        //Check if username already exists
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return  false;
        }
    }
    public Boolean chkUserNamePassword(String username,String password){
        //Check if password already exists
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return  false;
        }
    }
    //Products

}