package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {


    public dbHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id TEXT primary key, name TEXT ,address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertUserData(String id, String name, String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("address",address);
        long result = DB.insert("Userdetails",null,cv);
        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateUserData(String id, String name, String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("address",address);
        long result = DB.update("Userdetails",cv,"id=?",new String[] {id});
        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean deleteUserData(String id, String name, String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("Userdetails","id=?",new String[] {id});
        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }

    public Cursor getDate()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from Userdetails",null);
    }
}
