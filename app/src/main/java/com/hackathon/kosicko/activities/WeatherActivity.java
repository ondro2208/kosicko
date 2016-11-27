package com.hackathon.kosicko.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hackathon.kosicko.R;
import com.hackathon.kosicko.classes.intrfc.WeatherundergroundService;
import com.hackathon.kosicko.classes.models.Weather;
import com.hackathon.kosicko.handlers.ForecastAdapter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private String BASE_URL;
    private String APPKEY;
    private Retrofit retrofit;
    public static final Map<String, String> descImgMap;

    static {
        descImgMap = new HashMap<>();
        descImgMap.put("chancesleet", "sleet");
        descImgMap.put("sleet", "sleet");
        descImgMap.put("chancerain", "rain");
        descImgMap.put("rain", "rain");
        descImgMap.put("chancetstorms", "tstorms");
        descImgMap.put("tstorms", "tstorms");
        descImgMap.put("chancesnow", "snow");
        descImgMap.put("snow", "snow");
        descImgMap.put("chanceflurries", "snow");
        descImgMap.put("flurries", "snow");
        descImgMap.put("clear", "clear");
        descImgMap.put("sunny", "clear");
        descImgMap.put("mostlycloudy", "partlysunny");
        descImgMap.put("mostlysunny", "partlysunny");
        descImgMap.put("partlycloudy", "partlysunny");
        descImgMap.put("partlysunny", "partlysunny");
        descImgMap.put("fog", "fog");
        descImgMap.put("hazy", "fog");
        descImgMap.put("cloudy", "cloudy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        this.BASE_URL = getString(R.string.provider_api_url);
        this.APPKEY = getString(R.string.api_key);
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherundergroundService service = this.retrofit.create(WeatherundergroundService.class);

        String langCode = Locale.getDefault().getLanguage().toUpperCase();

        Call<Weather> allWeatherInfo = service.getWeatherInfo(this.APPKEY, langCode, "Slovakia", "Kosice");
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

                Log.i(TAG, "Download succeeded");

                Weather weatherInfo = response.body();

                ForecastAdapter fa = new ForecastAdapter(weatherInfo.forecast);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                RecyclerView view = (RecyclerView) findViewById(R.id.multi_day_forecast);
                view.setLayoutManager(layoutManager);
                view.setAdapter(fa);

                String currentIcon = descImgMap.get(weatherInfo.currentObservation.icon);
                if (weatherInfo.currentObservation.icon.startsWith("nt_")){
                    currentIcon = String.format("nt_%s", descImgMap.get(weatherInfo.currentObservation.icon));
                }
                RelativeLayout weatherBg = (RelativeLayout) findViewById(R.id.current_weather);
                int resId =  getResources().getIdentifier(
                        String.format("bg_%s", currentIcon),
                        "drawable",
                        getPackageName()
                );
                weatherBg.setBackground(getResources().getDrawable(resId));
                ImageView imgDesc = (ImageView) findViewById(R.id.img_desc);
                resId =  getResources().getIdentifier(
                        currentIcon,
                        "drawable",
                        getPackageName()
                );
                imgDesc.setImageDrawable(getResources().getDrawable(resId));
                TextView temp = (TextView) findViewById(R.id.temp);
                temp.setText(String.format("%s °C", weatherInfo.currentObservation.tempC));
                TextView desc = (TextView) findViewById(R.id.desc);
                desc.setText(weatherInfo.currentObservation.weather);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "Error downloading data:");
                t.printStackTrace();
            }
        });
    }

    public void onProviderClicked(View view) {
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.weather_provider))
        );
        startActivity(intent);
    }
}
