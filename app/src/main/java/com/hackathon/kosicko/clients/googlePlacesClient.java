package com.hackathon.kosicko.clients;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.hackathon.kosicko.activities.BeerActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Matt on 26.11.2016.
 */

public class GooglePlacesClient extends AppCompatActivity  implements OnConnectionFailedListener {
    private GoogleApiClient placesClient;
    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Resolving State
    private static final String STATE_RESOLVING_ERROR = "resolving_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;


    private static final int REQUEST_LOCATION = 1100;

    private static final String PLACES_RADARSEARCH_URL =  "https://maps.googleapis.com/maps/api/place/radarsearch/json?";
    private static final String APP_KEY = "&key=AIzaSyAMqmKzz65ak2oP7EiztXIoL7brIYtE7lU";
    private static final String RADIUS = "&radius=5000";
    private static final String TYPE_RESTAURANT = "&type=restaurant";
    private static final String TYPE_PARKING = "&type=parking";
    private static final String LOCATION_KE = "location=48.721614,21.257382";

    private static final String TAG_GET =  "PLACES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        placesClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
    }


    public void performSearch() throws Exception {
        new RetrieveGETTask(getApplicationContext()).execute();
        /*try {
            System.out.println("Perform Search ....");
            System.out.println("-------------------");
            HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
            HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
            request.url.put("key", API_KEY);
            request.url.put("location", latitude + "," + longitude);
            request.url.put("radius", 500);
            request.url.put("sensor", "false");

            if (PRINT_AS_STRING) {
                System.out.println(request.execute().parseAsString());
            } else {

                PlacesList places = request.execute().parseAs(PlacesList.class);
                System.out.println("STATUS = " + places.status);
                for (Place place : places.results) {
                    System.out.println(place);
                }
            }

        } catch (HttpResponseException e) {
            System.err.println(e.response.parseAsString());
            throw e;
        }*/

    }


    private void getLastLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            // permission has been granted, continue as usual
            Location myLocation =
                    LocationServices.FusedLocationApi.getLastLocation(placesClient);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We can now safely use the API we requested access to
                Location myLocation =
                        LocationServices.FusedLocationApi.getLastLocation(placesClient);
            } else {
                // Permission was denied or request was cancelled
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                placesClient.connect();
            }
        } else {
            // Show dialog using GoogleApiAvailability.getErrorDialog()
            showErrorDialog(result.getErrorCode());
            mResolvingError = true;
        }
    }
    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "errordialog");
    }
    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mResolvingError = false;
    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((GooglePlacesClient) getActivity()).onDialogDismissed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!placesClient.isConnecting() &&
                        !placesClient.isConnected()) {
                    placesClient.connect();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    public class RetrieveGETTask extends AsyncTask<String, Void, JSONObject> {
        private Context context;

        private ProgressDialog progress;
        private String errorMessage = "Connection error";

        public RetrieveGETTask(Context context) {
           this.context=context.getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            this.progress = new ProgressDialog(GooglePlacesClient.this);
//            this.progress.setMessage("Searching...");
//            this.progress.show();
        }
        @Override
        protected JSONObject doInBackground(String... cities) {
//            try {
//                StringBuilder fullBuilder = Helper.getMethod(GET_URL,null);
//                fullBuilder = Helper.getMethod(GET_URL+"?page=2",fullBuilder);
//                return new JSONObject(fullBuilder.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
            URL url = null;
            try {
                StringBuilder getURL = new StringBuilder(PLACES_RADARSEARCH_URL);
                getURL.append(LOCATION_KE);
                getURL.append(RADIUS);
                getURL.append(TYPE_RESTAURANT);
                getURL.append(APP_KEY);

                //location=-33.8670522,151.1957362&radius=500&type=restaurant&key=AIzaSyCV3mff9_Bja2faeESSg-WKAqq7zXV8LT4
                Log.i(TAG_GET,getURL.toString());
                url = new URL(getURL.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(15000 /* milliseconds */);
                connection.setReadTimeout(10000 /* milliseconds */);
                connection.connect();
                Log.i(TAG_GET, String.format("Connecting to %s", url.toString()));
                Log.i(TAG_GET, String.format("HTTP Status Code: %d", connection.getResponseCode()));
                StringBuilder sb = new StringBuilder();//getDataStringBuilder(connection);
                BufferedReader br = new BufferedReader(new InputStreamReader( connection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                Log.i(TAG_GET, sb.toString());
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.i(TAG_GET, String.format("HTTP Status Code: %d", connection.getResponseCode()));
                    return null;
                }
                connection.disconnect();

                //if(fullBuilder==null){
                   // return stringBuilder;
               // }
                //String newData = stringBuilder.toString();
               // Log.i(TAG_GET, String.format("GET: %s", newData));
                //newData = newData.substring(newData.indexOf("[")+1,newData.indexOf("]"));
                //fullBuilder.insert(fullBuilder.indexOf("]"),","+newData);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
         //   this.progress.dismiss();
//            if (json == null) {
//                Toast.makeText(getApplicationContext(), this.errorMessage, Toast.LENGTH_SHORT).show();
//                return;
          //  }
            // change activity
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),BeerActivity.class);
//            intent.putExtra("json", json.toString());
//            intent.putExtra("durationF", durationF);
//            intent.putExtra("distanceF", distanceF);
            startActivity(intent);
        }
    }
}
