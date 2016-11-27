package com.hackathon.kosicko.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Peťo on 27.11.2016.
 */

public class BeerDBHandler extends SQLiteOpenHelper {

    //All static variables
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "BeerDB";

    //Database table
    private static final String TABLE_BEER = "beer";

    //Variables
    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private static final String KEY_PEOPLE = "people";


    public BeerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BEER_TABLE =
                " CREATE TABLE " + TABLE_BEER
                        + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_LAT + " TEXT, "
                        + KEY_LNG + " TEXT, "
                        + KEY_PEOPLE + " INTEGER "
                        + ")";

        db.execSQL(CREATE_BEER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEER);

        // Create tables again
        onCreate(db);
    }

    void addAnotherBeer(Beer beer) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_LAT, beer.getLat());
        values.put(KEY_LNG, beer.getLng());
        values.put(KEY_PEOPLE, beer.getPeople());

        // Inserting row
        db.insert(TABLE_BEER, null, values);
        db.close();
    }


    Cursor getData(SQLiteDatabase db) {

        String[] data = {KEY_LAT, KEY_LNG, KEY_PEOPLE};

        Cursor cursor = db.query(TABLE_BEER, data, null, null, null, null, null);

        return cursor;
    }

    ArrayList<Beer> getAllBeers(SQLiteDatabase db) {

        String[] data = {KEY_LAT, KEY_LNG, KEY_PEOPLE};

        Cursor cursor = db.query(TABLE_BEER, data, null, null, null, null, null);

        ArrayList<Beer> beerArrayList = new ArrayList<>();

        while(cursor.moveToNext()) {
            Beer beer = new Beer(cursor.getString(cursor.getColumnIndex(KEY_LAT)),
                                    cursor.getString(cursor.getColumnIndex(KEY_LNG)),
                                        cursor.getString(cursor.getColumnIndex(KEY_PEOPLE)));
            beerArrayList.add(beer);
        }

        return beerArrayList;
    }


    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

}
