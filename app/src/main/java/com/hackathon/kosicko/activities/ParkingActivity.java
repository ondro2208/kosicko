package com.hackathon.kosicko.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackathon.kosicko.R;
import com.hackathon.kosicko.clients.PlacesHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class ParkingActivity extends FragmentActivity implements OnConnectionFailedListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private String toActivity;
    JSONObject[] jsonObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        toActivity = intent.getStringExtra("toActivity");
        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(intent.getStringExtra("json"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObjects = PlacesHelper.jsonToClass(json);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMinZoomPreference(10);

        // Add a marker in Sydney and move the camera
        LatLng kosice = new LatLng(48.721614, 21.257382);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        PlacesHelper.addMarkersToMap(jsonObjects,mMap,toActivity,null);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kosice));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
