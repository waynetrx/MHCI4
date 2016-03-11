package com.mhci4.mhci4.library;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.concurrent.TimeUnit;

public class GoogleClient implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Context context;

    public GoogleClient(Context context, GoogleApiClient.ConnectionCallbacks listener) {
        this.context = context;
        mGoogleApiClient = new GoogleApiClient
                .Builder(context)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(listener)
                .build();
//        mGoogleApiClient.connect();
    }


    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    public void getCurrentPlace() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else
        {
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer placeLikelihoods) {
                    for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                        Log.i("onResult", String.format("Place '%s' has likelihood: %g and placeid: '%s'",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood(),
                                placeLikelihood.getPlace().getId()));
                    }
                    placeLikelihoods.release();
                }
            });
        }


    }

    public void getPlaceByID(String id)
    {
       PendingResult<PlaceBuffer> result =  Places.GeoDataApi.getPlaceById(mGoogleApiClient,id);
                result.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess()) {
                            final Place myPlace = places.get(0);
                            Log.i("GeoDataApi", "" + myPlace.getAddress());
                        }
                        else
                        Log.i("GeoDataApi","Status message: " + places.getStatus().getStatusMessage() + ", Status code: " + places.getStatus().getStatusCode());
                        places.release();
                    }
                });
    }



    public void getPlaceAutoCompleteNonBlocking(String query)
    {
        LatLngBounds bounds = null;
         Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient,query,bounds,null)
                .setResultCallback(new ResultCallback<AutocompletePredictionBuffer>() {
                    @Override
                    public void onResult(AutocompletePredictionBuffer autocompletePredictions) {

                        if(autocompletePredictions.getStatus().isSuccess())
                        {
                            Log.i("Autocomplete","autocomplete is success.");
                            for(AutocompletePrediction prediction : autocompletePredictions)
                            {
                                Log.i("AUTOCOMPLETE", "Place id: " + prediction.getPlaceId() + ", Description: " + prediction.getDescription());
                            }
                        }
                        else
                            Log.i("Autocomplete",autocompletePredictions.getStatus().getStatusMessage());

                        autocompletePredictions.release();
                    }
                },2, TimeUnit.SECONDS);
    }



    @Override
    public void onConnected(Bundle bundle) {
        Log.i("mGoogleApiClient", "GoogleApiClient connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("mGoogleApiClient","GoogleApiClient connection suspended");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("onConnectionFailed", "Error code: " + connectionResult.getErrorCode() + ", Message:" + connectionResult.getErrorMessage());

    }


}
