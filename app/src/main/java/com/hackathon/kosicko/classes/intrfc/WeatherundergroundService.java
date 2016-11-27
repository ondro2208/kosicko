package com.hackathon.kosicko.classes.intrfc;

import com.hackathon.kosicko.classes.models.Conditions;
import com.hackathon.kosicko.classes.models.MultiDayForecast;
import com.hackathon.kosicko.classes.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by developer on 26.11.2016.
 */

public interface WeatherundergroundService {

    // gets all weather info (current conditions and multiple day forecast) for a given locality
    @GET("{appkey}/conditions/forecast/lang:{langcode}/q/{country}/{city}.json")
    Call<Weather> getWeatherInfo(@Path("appkey") String appkey,
                                 @Path("langcode") String langCode,
                                 @Path("country") String country,
                                 @Path("city") String city);

    // gets current weather conditions for a given locality
    @GET("{appkey}/conditions/lang:{langcode}/q/{country}/{city}.json")
    Call<Conditions> getCurrentConditions(@Path("appkey") String appkey,
                                          @Path("langcode") String langCode,
                                          @Path("country") String country,
                                          @Path("city") String city);

    // gets multiple day forecast for a given locality
    @GET("{appkey}/forecast/lang:{langcode}/q/{country}/{city}.json")
    Call<MultiDayForecast> getMultiDayForecast(@Path("appkey") String appkey,
                                               @Path("langcode") String langCode,
                                               @Path("country") String country,
                                               @Path("city") String city);
}
