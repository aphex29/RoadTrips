package com.pmar.roadtrip.route;

import com.google.maps.model.LatLng;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="ROUTE_TBL")
public class Route {

    @Id
    @SequenceGenerator(
            name = "route_sequence",
            sequenceName = "route_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "route_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(name="user_id",
            updatable = false)
    private Long userId;

    @Column(name="origin",
            nullable = false)
    private String origin;

    @Column(name="destination",
            nullable = false)
    private String destination;

    @Column(name="startLat",
            nullable = false)
    private double startLat;

    @Column(name="startLng",
            nullable = false)
    private double startLng;

    @Column(name="endLat",
            nullable = false)
    private double endLat;

    @Column(name="endLng",
            nullable = false)
    private double endLng;

    @Column(name="distance",
            nullable = false)
    private double distance;

    @Column(name="duration",
            nullable = false)
    private Long duration;


    public Route(){

    }
    public Route(Long userId, String origin, String destination, double distance, Long duration, double startLat, double startLng, double endLat, double endLng){
        this.userId= userId;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.startLng = startLng;
        this.startLat = startLat;
        this.endLng = endLng;
        this.endLat = endLat;
    }

    public String getOrigin(){return origin;}
    public String getDestination(){return destination;}
    public double getDistance(){return distance;}
    public Long getDuration(){return duration;}

    public double getStartLat(){return startLat;}
    public double getStartLng(){return startLng;}
    public double getEndLat(){return endLat;}
    public double getEndLng(){return endLng;}
    public Long getId(){return id;}

    public void setOrigin(String newOrigin){origin = newOrigin;}
    public void setDestination(String newDestination){destination=newDestination;}
    public void setDistance(double newDistance){distance=newDistance;}
    public void setDuration(Long newDuration){duration=newDuration;}

    public void setStartLat(double newStartLat){startLat=newStartLat;}
    public void setStartLng(double newStartLng){startLng=newStartLng;}
    public void setEndLat(double newEndLat){endLat=newEndLat;}
    public void setEndLng(double newEndLng){endLng=newEndLng;}

    public String toString(){
        double miles = distance*0.000621371;
        int hours = (int)(distance/60);
        int minutes = (int)(duration%60);
        return String.format("Route[Route ID: %d, Origin: %s, Destination: %s, Distance: %.2fmi, Total Time: %dh%dm]",id,origin,destination,miles,hours,minutes);
    }
}
