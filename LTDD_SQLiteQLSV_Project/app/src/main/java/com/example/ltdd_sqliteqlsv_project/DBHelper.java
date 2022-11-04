package com.example.ltdd_sqliteqlsv_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table SinhVien(hoTen TEXT primary key, maSV TEXT, ngaySinh TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists SinhVien");
    }
    public Boolean insertuserdata(String hoTen, String maSV, String ngaySinh)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", hoTen);
        contentValues.put("maSV", maSV);
        contentValues.put("ngaySinh", ngaySinh);
        long result=DB.insert("SinhVien", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String hoTen, String maSV, String ngaySinh)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSV", maSV);
        contentValues.put("ngaySinh", ngaySinh);
        Cursor cursor = DB.rawQuery("Select * from SinhVien where hoTen = ?", new String[]{hoTen});
        if (cursor.getCount() > 0) {
            long result = DB.update("SinhVien", contentValues, "hoTen=?", new String[]{hoTen});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String hoTen)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from SinhVien where hoTen = ?", new String[]{hoTen});
        if (cursor.getCount() > 0) {
            long result = DB.delete("SinhVien", "hoTen=?", new String[]{hoTen});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from SinhVien", null);
        return cursor;
    }
}
