package com.mhci4.mhci4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mhci4.mhci4.library.DelayedAutoCompleteTextView;
import com.mhci4.mhci4.library.GoogleClient;
import com.mhci4.mhci4.library.PlaceAutoComplete;
import com.mhci4.mhci4.library.PlaceAutoCompleteAdapter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private DelayedAutoCompleteTextView ac_search;
    private GoogleApiClient mClient;
    private PlaceAutoCompleteAdapter adapter;
    private PlaceAutoComplete placeAutoComplete;
    private Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ac_search = (DelayedAutoCompleteTextView)findViewById(R.id.ac_map_search);
        btnConfirm = (Button)findViewById(R.id.btn_map_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(placeAutoComplete != null)
                {
                    Bundle bundle = getIntent().getExtras();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("location",placeAutoComplete);
                    setResult(MainActivity.RESULT_SUCCESS,returnIntent);
                    finish();


                }
            }
        });

        GoogleClient gc = new GoogleClient(getApplicationContext(),this);
        mClient = gc.getmGoogleApiClient();
        mClient.connect();

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
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        adapter = new PlaceAutoCompleteAdapter(getApplicationContext(),R.layout.listview_location_item_row,null,null);
        adapter.setGoogleApiClient(mClient);
        ac_search.setAdapter(adapter);
        ac_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                placeAutoComplete = adapter.getItem(position);
                final String placeId = placeAutoComplete.getPlaceId().toString();
                Places.GeoDataApi.getPlaceById(mClient,placeId).setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if(places.getStatus().isSuccess() && places.getCount() > 0)
                        {
                            Place p = places.get(0);
                            LatLng latLng = p.getLatLng();
                            Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title(places.get(0).getName().toString()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(17),2000,null);
                        }
                        places.release();
                    }
                });
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
