package com.hackathon.kosicko.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 11/27/2016.
 */

public class EventsDBHandler extends SQLiteOpenHelper{

    //All static variables
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "EventsDB";

    //Database table
    private static final String TABLE_Events = "events";

    //Variables

    private static final String KEY_ID = "id";
    private static final String KEY_URL = "url";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_PLACE = "place";
    private static final String KEY_CATEGORY = "category";


    public EventsDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRAINING_TABLE =
                " CREATE TABLE " + TABLE_Events
                        + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_URL + " TEXT,"
                        + KEY_NAME + " TEXT, "
                        + KEY_DATE + " TEXT, "
                        + KEY_TIME + " TEXT, "
                        + KEY_PLACE + " TEXT, "
                        + KEY_CATEGORY + " TEXT "
                        + ")";

        db.execSQL(CREATE_TRAINING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Events);

        // Create tables again
        onCreate(db);
    }

    void addAnotherEvents(Events events) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_URL, events.getUrl());
        values.put(KEY_NAME, events.getName());
        values.put(KEY_DATE, events.getDate());
        values.put(KEY_TIME, events.getTime());
        values.put(KEY_PLACE, events.getPlace());
        values.put(KEY_CATEGORY, events.getCategory());

        // Inserting row
        db.insert(TABLE_Events, null, values);
        db.close();
    }

    Cursor getData(SQLiteDatabase db) {
        String[] data = {KEY_URL, KEY_NAME, KEY_DATE, KEY_TIME, KEY_PLACE, KEY_CATEGORY};
        Cursor cursor = db.query(TABLE_Events, data, null, null, null, null, null, null);
        return cursor;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public List<Events> getAllEvents(){
        List<Events> eventsList = new ArrayList<Events>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Events;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Events events = new Events();
                events.setId(Integer.parseInt(cursor.getString(0)));
                events.setUrl(cursor.getString(1));
                events.setName(cursor.getString(2));
                events.setDate(cursor.getString(3));
                events.setTime(cursor.getString(4));
                events.setPlace(cursor.getString(5));
                events.setCategory(cursor.getString(6));
                eventsList.add(events);
            } while (cursor.moveToNext());
        }
        return eventsList;
    }

    public int getEventsCount(){
        String countQuery = "SELECT  * FROM " + TABLE_Events;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public void dropTable(){
        String countQuery = "DROP TABLE " + TABLE_Events;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(countQuery);
    }

    public void createTable() {
        String CREATE_TRAINING_TABLE =
                " CREATE TABLE " + TABLE_Events
                        + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_URL + " TEXT,"
                        + KEY_NAME + " TEXT, "
                        + KEY_DATE + " TEXT, "
                        + KEY_TIME + " TEXT, "
                        + KEY_PLACE + " TEXT, "
                        + KEY_CATEGORY + " TEXT "
                        + ")";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(CREATE_TRAINING_TABLE);
    }


}
