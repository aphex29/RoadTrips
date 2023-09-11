package com.pmar.roadtrip.request;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

public class WaypointRequest implements DirectionsRequest{

    private GeoApiContext context;
    private String origin;
    private String destination;
    private String[] waypoints;

    public WaypointRequest(GeoApiContext context, String origin, String destination, String... waypoints){
        this.context = context;
        this.origin = origin;
        this.destination = destination;
        this.waypoints = waypoints;
    }
    public DirectionsResult request() {
        try{

            DirectionsResult result = DirectionsApi
                    .newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin(origin)
                    .waypoints(waypoints)
                    .destination(destination)
                    .await();
            return result;
        }
        catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }
}
