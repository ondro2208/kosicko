package com.hackathon.kosicko.clients;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.hackathon.kosicko.R;

/**
 * Created by Matt on 26.11.2016.
 */

public class PlacesClient extends FragmentActivity  implements OnConnectionFailedListener {
    private GoogleApiClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        placesClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }






    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
