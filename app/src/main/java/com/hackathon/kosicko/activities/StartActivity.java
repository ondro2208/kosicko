package com.hackathon.kosicko.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hackathon.kosicko.R;
import com.hackathon.kosicko.clients.GooglePlacesClient;


public class StartActivity extends AppCompatActivity {
    private RelativeLayout weather_button;
    private RelativeLayout news_button;
    private RelativeLayout events_button;
    private RelativeLayout parking_button;
    private RelativeLayout second_hand_button;
    private RelativeLayout beer_button;
//kk
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        weather_button = (RelativeLayout) findViewById(R.id.weather_button);
        news_button = (RelativeLayout) findViewById(R.id.news_button);
        events_button = (RelativeLayout) findViewById(R.id.events_button);
        parking_button = (RelativeLayout) findViewById(R.id.parking_button);
        second_hand_button = (RelativeLayout) findViewById(R.id.secondhand_button);
        beer_button = (RelativeLayout) findViewById(R.id.beer_button);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void onWeatherClicked(View view){
        Toast.makeText(this, getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(this, WeatherActivity.class);
//        startActivity(i);
    }

    public void onNewsClicked(View view){
        Intent i = new Intent(this, NewsActivity.class);
        startActivity(i);
    }

    public void onEventsClicked(View view){
        Toast.makeText(this, getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(this, EventsActivity.class);
//        startActivity(i);
    }

    public void onParkingClicked(View view){
        Toast.makeText(this, getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(this, ParkingActivity.class);
//        startActivity(i);
    }

    public void onSecondHandClicked(View view){
        Toast.makeText(this, getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(this, SecondHandActivity.class);
//        startActivity(i);
    }

    public void onBeerClicked(View view) throws Exception {
//        Toast.makeText(this, getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(this, BeerActivity.class);
        //startActivity(i);
        GooglePlacesClient places = new GooglePlacesClient();
        places.performSearch();
    }

}
