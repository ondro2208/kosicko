package com.hackathon.kosicko.classes;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by otara on 26.11.2016.
 */

public class PlacesHelper {
    private static final String TAG_GET = "GET";
    private static final String TAG_POST= "POST";

    private static final String PLACES_RADARSEARCH_URL =  "https://maps.googleapis.com/maps/api/place/radarsearch/json?";
    private static final String APP_KEY = "&key=AIzaSyAMqmKzz65ak2oP7EiztXIoL7brIYtE7lU";
    private static final String RADIUS = "&radius=5000";
    private static final String TYPE_RESTAURANT = "&type=restaurant";
    private static final String TYPE_PARKING = "&type=parking";
    private static final String LOCATION_KE = "location=48.721614,21.257382";


    public static StringBuilder getMethod(String toAction){

        StringBuilder getURL = new StringBuilder();
        if("beer".equals(toAction)){
            getURL.append(PLACES_RADARSEARCH_URL);
            getURL.append(LOCATION_KE);
            getURL.append(RADIUS);
            getURL.append(TYPE_RESTAURANT);
            getURL.append(APP_KEY);
        }
        else if("parking".equals(toAction)){

        }
        URL url = null;
        try {
            url = new URL(getURL.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.connect();

            Log.i(TAG_GET, String.format("Connecting to %s", url.toString()));
            Log.i(TAG_GET, String.format("HTTP Status Code: %d", connection.getResponseCode()));
            StringBuilder stringBuilder = getDataStringBuilder(connection);
            //StringBuilder sb = getDataStringBuilder(connection);
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.i(TAG_GET, String.format("HTTP Status Code: %d", connection.getResponseCode()));
                return null;
            }
            connection.disconnect();
            return stringBuilder;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private static StringBuilder getDataStringBuilder(HttpURLConnection connection) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader( connection.getInputStream(),"utf-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        Log.i(TAG_GET, sb.toString());
        return sb;
    }
}
