package com.pmar.roadtrip.route;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="ROUTE_TBL")
public class Route {
    @Value("${apiKey}")
    private String apiKey;

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

    @Column(name="origin",
            nullable = false)
    private String origin;

    @Column(name="destination",
            nullable = false)
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
        return String.format("Route[Origin: %s, Destination: %s, Distance: %fmi, Total Time: %dh%dm]",origin,destination,distance,hours,minutes);
    }
}
