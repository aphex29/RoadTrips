package com.pmar.roadtrip.request;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

public class DirectionsRequestImpl implements DirectionsRequest<DirectionsResult> {
    private GeoApiContext context;
    private String origin;
    private String destination;
    private String[] waypoints;

    public DirectionsRequestImpl(){}
    public DirectionsRequestImpl(GeoApiContext context, String origin, String destination, String... waypoints){
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

    public DirectionsResult requestWaypointRoute(String waypoints){
        try{
            DirectionsResult result = DirectionsApi
                    .newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin(origin)
                    .destination(destination)
                    //Requesting a route with waypoints to create a new map for user. Send either String address(es) or Long/Lat coordinates
                    .waypoints(waypoints)
                    .await();
            return result;
        }
        catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }

}
