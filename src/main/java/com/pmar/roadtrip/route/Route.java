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
        return String.format("Route[Route ID: %s, Origin: %s, Destination: %s, Distance: %.2fmi, Total Time: %dh%dm]",id.toString(),origin,destination,miles,hours,minutes);
    }

    public ObjectId getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getStartLat() {
        return startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public double getEndLat() {
        return endLat;
    }

    public double getEndLng() {
        return endLng;
    }

    public double getDistance() {
        return distance;
    }

    public Long getDuration() {
        return duration;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public void setEndLng(double endLng) {
        this.endLng = endLng;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
