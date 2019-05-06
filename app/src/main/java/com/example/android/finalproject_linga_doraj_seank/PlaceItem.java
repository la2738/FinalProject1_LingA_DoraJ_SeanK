package com.example.android.finalproject_linga_doraj_seank;

import com.google.android.gms.maps.model.LatLng;

public class PlaceItem {
    private String name ;
    private double lat ;
    private double lng ;
    private String label ;

    public PlaceItem(String name, double lat, double lng, String label) {
        this.name = name ;
        this.lat = lat ;
        this.lng = lng ;
        this.label = label ;
    }

    public String getName() {
        return this.name ;
    }
    public double getLat() {
        return this.lat ;
    }
    public double getLng() {
        return this.lng ;
    }
    public String getLabel() {
        return this.label;
    }

    public LatLng getLatLng() {
        return new LatLng(this.lat, this.lng);
    }

}
