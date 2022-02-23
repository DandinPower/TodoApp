package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "database_name20";
    public static final String TODO_TABLE = "TODO_TABLE";
    public static final String TODO_TITLE = "TODO_TITLE";
    public static final String TODO_INFO = "TODO_INFO";
    public static final String TODO_DUE = "TODO_DUE";
    public static final String TODO_DO = "TODO_DO";
    public static final String COLUMN_ID = "ID";
    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableStatement = "CREATE TABLE " + TODO_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ TODO_TITLE + " TEXT, " +  TODO_INFO + " TEXT, " + TODO_DUE + " TEXT, " + TODO_DO + " TEXT)";
        db.execSQL(createTableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        onCreate(db);
    }
    public boolean Add(String Title, String Info, String Due, String Do){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TODO_TITLE,Title);
        cv.put(TODO_INFO,Info);
        cv.put(TODO_DUE,Due);
        cv.put(TODO_DO,Do);

        long insert = sqLiteDatabase.insert(TODO_TABLE,null,cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean Delete(String Id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TODO_TABLE + " WHERE " + COLUMN_ID + " = " + Id;
        Cursor cursor = sqLiteDatabase.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean Update(String Id,String Title, String Info, String Due, String Do){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString1 = "UPDATE " + TODO_TABLE + " SET "+ TODO_TITLE + " = '" + Title + "' WHERE " + COLUMN_ID + " = " + Id;
        String queryString2 = "UPDATE " + TODO_TABLE + " SET "+ TODO_INFO + " = '" + Info + "' WHERE " + COLUMN_ID + " = " + Id;
        String queryString3 = "UPDATE " + TODO_TABLE + " SET "+ TODO_DUE + " = '" + Due + "' WHERE " + COLUMN_ID + " = " + Id;
        String queryString4 = "UPDATE " + TODO_TABLE + " SET "+ TODO_DO + " = '" + Do + "' WHERE " + COLUMN_ID + " = " + Id;
        //Log.d("sql",queryString1);
        sqLiteDatabase.execSQL(queryString1);
        sqLiteDatabase.execSQL(queryString2);
        sqLiteDatabase.execSQL(queryString3);
        sqLiteDatabase.execSQL(queryString4);
        return true;

    }
    public ArrayList<String> GetId(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String>arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TODO_TABLE,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> GetTitle(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String>arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TODO_TABLE,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<String> GetInfo(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String>arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TODO_TABLE,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(2));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<String> GetDue(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String>arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TODO_TABLE,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(3));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<String> GetDo(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String>arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TODO_TABLE,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(4));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public void removeTodo(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM "+TODO_TABLE);
    }
}
