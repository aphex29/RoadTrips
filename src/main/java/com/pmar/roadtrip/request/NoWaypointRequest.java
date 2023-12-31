package com.pmar.roadtrip.request;

import com.google.maps.DirectionsApi;
import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

public class NoWaypointRequest implements DirectionsRequest {
    private GeoApiContext context;
    private String origin;
    private String destination;


    public NoWaypointRequest(){}
    public NoWaypointRequest(GeoApiContext context, String origin, String destination){
        this.context = context;
        this.origin = origin;
        this.destination = destination;

    }



    public DirectionsResult request() {
        try{

            DirectionsResult result = DirectionsApi
                    .newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin(origin)
                    .destination(destination)
                    .await();
            return result;
        }
        catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }



}
