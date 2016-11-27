package com.hackathon.kosicko.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackathon.kosicko.R;
import com.hackathon.kosicko.clients.GooglePlacesClient;
import com.hackathon.kosicko.clients.PlacesHelper;
import com.hackathon.kosicko.handlers.Beer;
import com.hackathon.kosicko.handlers.BeerDBHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeerActivity extends FragmentActivity implements OnMapReadyCallback,OnConnectionFailedListener,GoogleMap.OnMarkerClickListener {
//gf
    private GoogleMap mMap;
    private JSONObject[] places;
    private LatLng myBeer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GooglePlacesClient googlePlacesClient=new GooglePlacesClient();
        Intent intent=getIntent();
        try {
            JSONObject jsonObject=new JSONObject(intent.getStringExtra("json"));
            if(jsonObject!=null){
                places=PlacesHelper.jsonToClass(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        BeerDBHandler beerDbHandler=new BeerDBHandler(getApplicationContext());
        SQLiteDatabase db = beerDbHandler.getReadableDatabase();
        //beerDbHandler.addAnotherBeer(new Beer("48.7205288","21.2599915",1));
       ArrayList<Beer> beers=beerDbHandler.getAllBeers(db);
       ;///lll
//        try {
//            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.getContext(), R.raw.map_style));
//            if (!success) {
//                Log.e("MapsActivityRaw", "Style parsing failed.");
//            }
//        } catch ( Resources.NotFoundException e) {
//            Log.e("MapsActivityRaw", "Can't find style.", e);
//        }
        if(this.places!=null);
            PlacesHelper.addMarkersToMap(places,mMap,"beer",beers);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(48.721614, 21.257382);
        mMap.addMarker(new MarkerOptions()
                .position(sydney).
                        title("My position").icon(BitmapDescriptorFactory.defaultMarker()));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraUpdate startLocation = CameraUpdateFactory.newLatLngZoom(sydney, 15);
        mMap.animateCamera(startLocation);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onBackPressed(){
        Intent back=new Intent(getApplicationContext(),StartActivity.class);
        startActivity(back);
        finish();


    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
            marker.getPosition();
        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            return true;

    }

    private void createBeerEvent(double lat, double lng){

    }

}
