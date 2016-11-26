package com.hackathon.kosicko.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hackathon.kosicko.R;
import com.hackathon.kosicko.classes.intrfc.WeatherundergroundService;
import com.hackathon.kosicko.classes.models.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private String BASE_URL;
    private Retrofit retrofit;
    private TextView wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        this.wind = (TextView) findViewById(R.id.wind);

        this.BASE_URL = getString(R.string.provider_api_url);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherundergroundService service = this.retrofit.create(WeatherundergroundService.class);

        Call<Weather> allWeatherInfo = service.getWeatherInfo("EN", "Slovakia", "Kosice");
        allWeatherInfo.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                // Uncomment if debugging errors here
                /*Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));

                if(response.isSuccessful()) {
                    Log.d("Response body", response.body().currentObservation.toString());
                    Log.d("Response body", response.body().forecast.toString());
                    Log.d("Response body", response.body().currentObservation.windKph.toString());
                } else  {
                    Log.d("Response errorBody", String.valueOf(response.errorBody()));
                }*/

                Weather weatherInfo = response.body();

                Log.i(TAG, "Download succeeded");

                wind.setText(weatherInfo.currentObservation.windKph.toString());

                // TODO: now design the activity layout and create a listview adapter to display data for multiday forecast
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "Error downloading data:");
                t.printStackTrace();
            }
        });
    }
}
