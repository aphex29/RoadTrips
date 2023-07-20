package com.pmar.roadtrip.route;

import org.springframework.beans.factory.annotation.Value;

public class Route {
    @Value("${apiKey}")
    private String apiKey;

    private String origin;
    private String destination;
    private double distance;


    private int hours;
    private int minutes;


    public Route(){

    }
    public Route(String origin, String destination, double distance, int hours, int minutes){
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.hours = hours;
        this.minutes = minutes;

    }



    public String toString(){
        return String.format("starting at: %s --> ending at: %s\nDistance: %fmi --- Total time: %dh%dm",origin,destination,distance,hours,minutes);
    }
}
