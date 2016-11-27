package com.hackathon.kosicko.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Peťo on 26.11.2016.
 */

public class NewsDBHandler extends SQLiteOpenHelper {

    //All static variables
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "NewsDB";

    //Database table
    private static final String TABLE_News = "news";

    //Variables
    private static final String KEY_ID = "id";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_URL = "url";
    private static final String KEY_DATE = "date";


    public NewsDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRAINING_TABLE =
                " CREATE TABLE " + TABLE_News
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SOURCE + " TEXT,"
                + KEY_TITLE + " TEXT, "
                + KEY_IMAGE + " TEXT, "
                + KEY_URL + " TEXT, "
                + KEY_DATE + " TEXT "
                + ")";

        db.execSQL(CREATE_TRAINING_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_News);

        // Create tables again
        onCreate(db);
    }

    void addAnotherNews(News news) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SOURCE, news.getSource());
        values.put(KEY_TITLE, news.getTitle());
        values.put(KEY_IMAGE, news.getImage());
        values.put(KEY_URL, news.getUrl());
        values.put(KEY_DATE, news.getDate());

        // Inserting row
        db.insert(TABLE_News, null, values);
        db.close();
    }


    Cursor getData(SQLiteDatabase db) {

        String[] data = {KEY_SOURCE, KEY_TITLE, KEY_IMAGE, KEY_URL, KEY_DATE};

        Cursor cursor = db.query(TABLE_News, data, null, null, null, null, null);

        return cursor;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

}
