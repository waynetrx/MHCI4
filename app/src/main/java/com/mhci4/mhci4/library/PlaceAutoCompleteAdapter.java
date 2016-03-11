package com.mhci4.mhci4.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;
import com.mhci4.mhci4.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class PlaceAutoCompleteAdapter extends ArrayAdapter<PlaceAutoComplete> implements Filterable, ResultCallback<AutocompletePredictionBuffer> {

    private Context mContext;
    private LatLngBounds mBounds;
    private AutocompleteFilter mPlaceFilter;
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<PlaceAutoComplete> mPlaces;

    public PlaceAutoCompleteAdapter(Context context, int resource, LatLngBounds bounds, AutocompleteFilter filter) {
        super(context, resource);
        this.mContext = context;
        this.mBounds = bounds;
        this.mPlaceFilter = filter;
        mPlaces = new ArrayList<PlaceAutoComplete>();
    }

    @Override
    public PlaceAutoComplete getItem(int position) {
        return mPlaces.get(position);
    }

    @Override
    public int getCount() {
        return mPlaces.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_location_item_row,parent,false);
        }
        PlaceAutoComplete pac = getItem(position);
        TextView list_desc = (TextView)convertView.findViewById(R.id.actv_location_desc);
        list_desc.setText(pac.getDesc().toString());
        return convertView;
    }

    public void setGoogleApiClient(GoogleApiClient client)
    {
        if (client == null || !client.isConnected()) {
            mGoogleApiClient = null;
        } else {
            mGoogleApiClient = client;
        }
    }



    public void getPredictions(CharSequence query)
    {
        mPlaces.clear();

        if(this.mGoogleApiClient != null)
        {
            PendingResult<AutocompletePredictionBuffer> result = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient,query.toString(),null,null);
            AutocompletePredictionBuffer buffer = result.await(60, TimeUnit.SECONDS);
            final Status status = buffer.getStatus();
            if(status.isSuccess())
            {
                Iterator<AutocompletePrediction> iterator = buffer.iterator();
                while (iterator.hasNext())
                {
                    AutocompletePrediction prediction = iterator.next();
                    mPlaces.add(new PlaceAutoComplete(prediction.getPlaceId(),prediction.getDescription()));

                }
            }
            buffer.release();
        }
        else
            Log.i("GoogleApi","Google api is not connected.");

    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if(constraint != null)
                {
                    getPredictions(constraint);
                    if(mPlaces.size() > 0)
                    {
                        results.values = mPlaces;
                        results.count = mPlaces.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if(results != null && results.count > 0)
                {
                    notifyDataSetChanged();
                }
                else
                    notifyDataSetInvalidated();
            }
        };
        return filter;
    }


    @Override
    public void onResult(AutocompletePredictionBuffer autocompletePredictions) {

        if(autocompletePredictions.getStatus().isSuccess())
        {
            for(AutocompletePrediction prediction : autocompletePredictions)
            {
                PlaceAutoComplete place = new PlaceAutoComplete(prediction.getPlaceId(),prediction.getDescription());
                mPlaces.add(place);
            }
        }
    }
}
