package com.mhci4.mhci4.library;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MyLocation {

    private String imageLink;
    private LatLng latlngPoint;
    private Bitmap bmp;
    private Marker marker;

    public MyLocation(String imageLink, LatLng latlngPoint) {
        this.imageLink = imageLink;
        this.latlngPoint = latlngPoint;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public LatLng getLatlngPoint() {
        return latlngPoint;
    }

    public void setLatlngPoint(LatLng latlngPoint) {
        this.latlngPoint = latlngPoint;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if(o instanceof MyLocation)
        {
            Marker marker = (Marker)o;
            if(this.marker.equals(marker))
            {
                result = true;
            }
        }

        return result;
    }
}
