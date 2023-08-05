package com.pmar.roadtrip.route;

import com.google.maps.model.LatLng;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection="route")
public class Route {

    @Id
    private ObjectId id;

    private String origin;

    private String destination;

    private double startLat;

    private double startLng;

    private double endLat;

    private double endLng;

    private double distance;

    private Long duration;

    public Route(){}
    public Route(String origin, String destination, double distance, Long duration, double startLat, double startLng, double endLat, double endLng){

        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.startLng = startLng;
        this.startLat = startLat;
        this.endLng = endLng;
        this.endLat = endLat;
    }

    public String toString(){
        double miles = distance*0.000621371;
        int hours = (int)(distance/60);
        int minutes = (int)(duration%60);
        return String.format("Route[Route ID: %d, Origin: %s, Destination: %s, Distance: %.2fmi, Total Time: %dh%dm]",id,origin,destination,miles,hours,minutes);
    }

    public ObjectId getId(){
        return id;
    }



}
