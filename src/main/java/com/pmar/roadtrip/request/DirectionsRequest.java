package com.pmar.roadtrip.request;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.TravelMode;

public class DirectionsRequest implements GeoContext<DirectionsResult> {
    private GeoApiContext context;
    private String origin;
    private String destination;

    public DirectionsRequest(){}
    public DirectionsRequest(GeoApiContext context, String origin, String destination){
        this.context = context;
        this.origin = origin;
        this.destination = destination;
    }



    public DirectionsResult execute() {
        try{
            DirectionsResult result = DirectionsApi
                    .newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin(origin)
                    .destination(destination)
                    .await();

//            Double startLat = result.routes[0].legs[0].startLocation.lat;
//            Double startLng = result.routes[0].legs[0].startLocation.lng;

//            String duration = result.routes[0].legs[0].duration.humanReadable;
//            String distance = result.routes[0].legs[0].distance.humanReadable;



            return result;
        }
        catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }
}
