package com.hackathon.kosicko.clients;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackathon.kosicko.R;
import com.hackathon.kosicko.handlers.Beer;
import com.hackathon.kosicko.handlers.BeerDBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by otara on 26.11.2016.
 */

public class PlacesHelper {
    private static final String TAG_GET = "GET";

    private static final String PLACES_NEARBYSEARCH_URL =  "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    private static final String APP_KEY = "&key=AIzaSyAMqmKzz65ak2oP7EiztXIoL7brIYtE7lU";
    private static final String RADIUS = "&radius=5000";
    private static final String TYPE_RESTAURANT = "&type=restaurant";
    private static final String TYPE_PARKING = "&type=parking";
    private static final String LOCATION_KE = "location=48.721614,21.257382";


    public static StringBuilder getMethod(String toAction){

        StringBuilder getURL = new StringBuilder();
        if("beer".equals(toAction)){
            getURL.append(PLACES_NEARBYSEARCH_URL);
            getURL.append(LOCATION_KE);
            getURL.append(RADIUS);
            getURL.append(TYPE_RESTAURANT);
            getURL.append(APP_KEY);
        }
        else if("parking".equals(toAction)){
            getURL.append(PLACES_NEARBYSEARCH_URL);
            getURL.append(LOCATION_KE);
            getURL.append(RADIUS);
            getURL.append(TYPE_PARKING);
            getURL.append(APP_KEY);
        }
        URL url = null;
        Log.d("PlacesHelper", "URL: " + getURL.toString());
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

    public static JSONObject[] jsonToClass(JSONObject jsonObject) {

        JSONArray arr = null;
        try {
            arr = new JSONArray(jsonObject.getString("results"));

            JSONObject[] finalObject = new JSONObject[arr.length()];

            for(int i = 0; i < arr.length(); i++){
                JSONObject js = new JSONObject();
                js.put("lat", arr.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat"));
                js.put("lng", arr.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng"));
                js.put("name", arr.getJSONObject(i).getString("name"));
                js.put("place_id", arr.getJSONObject(i).getString("place_id"));
                finalObject[i]=js;

            }

            for(int k=0;k<finalObject.length-1;k++){
                //finalObject[k].get("name");
                Log.d("name: ",finalObject[k].toString());//get("name").toString());
            }

            return finalObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMarkersToMap(JSONObject[] jsonObjects, GoogleMap googleMap, String toActivity, ArrayList<Beer> beerEvents){

        for(JSONObject object : jsonObjects){
            double lat = 0;
            double lon = 0;
            String name = null;
            try {
                lat = object.getDouble("lat");
                lon = object.getDouble("lng");
                name = object.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LatLng position = new LatLng(lat,lon);
            MarkerOptions startMarker = null;
            if("beer".equals(toActivity)){
                startMarker = new MarkerOptions();
                startMarker.position(position).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.establishment));
                if(beerEvents!=null){
                    for(Beer beer: beerEvents){
                        if(Double.valueOf(beer.getLat())==lat && Double.valueOf(beer.getLng())==lon){
                            startMarker.position(position).title(name).snippet(String.valueOf(beer.getPeople())).icon(BitmapDescriptorFactory.fromResource(R.drawable.beer_marker));
                        }
                }


                }
            }
            if("parking".equals(toActivity)) {
                startMarker = new MarkerOptions().position(position).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_map));
            }

            googleMap.addMarker(startMarker);
        }
    }
}
