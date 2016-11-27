package com.hackathon.kosicko.activities;

import android.content.Intent;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackathon.kosicko.R;
import com.hackathon.kosicko.clients.GooglePlacesClient;
import com.hackathon.kosicko.clients.PlacesHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class BeerActivity extends FragmentActivity implements OnMapReadyCallback,OnConnectionFailedListener,GoogleMap.OnMarkerClickListener {
//gf
    private GoogleMap mMap;
    private JSONObject[] places;


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
        if(this.places!=null);
            PlacesHelper.addMarkersToMap(places,mMap,"beer");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(48.721614, 21.257382);
        mMap.addMarker(new MarkerOptions()
                .position(sydney).
                        title("Marker in Sydney").snippet("Population: 4,627,300").icon(BitmapDescriptorFactory.fromResource(R.drawable.beer)));
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

    private void placeMarkers(){

    }
    @Override
    public boolean onMarkerClick(final Marker marker) {
            marker.getPosition();
        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            return true;

    }

}
