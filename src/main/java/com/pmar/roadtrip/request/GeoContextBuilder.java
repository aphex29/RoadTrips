package com.pmar.roadtrip.request;

import com.google.maps.GeoApiContext;


public class GeoContextBuilder {

    //Static field to maintain a singleton object
    private GeoApiContext.Builder builder = new GeoApiContext.Builder();


    public GeoContextBuilder(){}

    public GeoApiContext.Builder getBuilder(){return builder;}

}
