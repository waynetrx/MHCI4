package com.mhci4.mhci4.library;

import com.google.android.gms.maps.model.LatLng;

public class MyLocation {

    private String imageLink;
    private LatLng latlngPoint;

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
}
