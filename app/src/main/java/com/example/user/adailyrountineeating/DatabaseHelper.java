package com.example.user.adailyrountineeating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public  class DatabaseHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "EatingRoutine.db";
    public static final String TABLE_NAME = "EatingRoutine_table";
    public static final String COL1 ="NOTE";
    public static final String COL2 ="DATE";
    public static final String COL3 = "BREAKFAST";
    public static final String COL4 = "LAUNCH";
    public static final String COL5 = "DINNER";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "(NOTE INTEGER PRIMARY KEY AUTOINCREMENT ,"
    + "DATE TEXT,BREAKFAST TEXT , LAUNCH TEXT, DINNER TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public  boolean addData(String date,String breakfast , String launch , String dinner){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,date);
        contentValues.put(COL3,breakfast);
        contentValues.put(COL4,launch);
        contentValues.put(COL5,dinner);

        long result = db.insert(TABLE_NAME,null ,contentValues);

        if(result == -1){
            return  false;

        }
        else{

            return  true;

        }

    }

    public  Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  data;

    }

    public Boolean updateData(String note, String date, String breakfast, String launch, String dinner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,note);
        contentValues.put(COL2,date);
        contentValues.put(COL3,breakfast);
        contentValues.put(COL4,launch);
        contentValues.put(COL5,dinner);
        db.update(TABLE_NAME, contentValues, "NOTE = ?", new String[] {note});
        return true;
    }

    public Integer delete (String note){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NOTE = ?", new String[] {note});
    }
}