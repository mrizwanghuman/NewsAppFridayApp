package com.example.admin.newsappfridayapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Admin on 12/9/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "News";
    public static final String TABLE_NAME = "NewsArtical";
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_NAME ="NewsTitle";
    public static final String COLUMN_PUBLISH ="DatePublish";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ COLUMN_NAME+" TEXT PRIMARY KEY," + COLUMN_PUBLISH + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  long saveNewsHeadline(NewsHeadlineObject newsHeadLines){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,newsHeadLines.getTitle());
        contentValues.put(COLUMN_PUBLISH, newsHeadLines.getDatePublish());
        long row = db.insert(TABLE_NAME, null, contentValues);
        return  row;

    }
    public Cursor getNewsHeadLin(String title){
        List<NewsHeadlineObject> newsHeadlineObjectList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT "+COLUMN_NAME+ " FROM "+ TABLE_NAME+ " WHERE NewsTitle=?";
        Cursor cursor = database.rawQuery(QUERY, new String[] {title});
        if(cursor.moveToFirst()){
            do{
                NewsHeadlineObject person = new NewsHeadlineObject(cursor.getString(0));
                newsHeadlineObjectList.add(person);
            }while (cursor.moveToNext());
        }
        return cursor;
    }
}

