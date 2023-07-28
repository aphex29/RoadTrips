package com.pmar.roadtrip.request;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;

public class GeoContextBuilder {

    //Static field to prevent
    private static GeoApiContext context = new GeoApiContext.Builder().apiKey("${env.API_KEY}").build();

    public GeoContextBuilder(){}

    public GeoApiContext getContext(){
        return context;
    }

}
