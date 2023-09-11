package com.pmar.roadtrip.waypoint;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="waypoint")
public class Waypoint{

    @Id
    private ObjectId id;

    private String address;

    private String notes;

    private double longitude;

    private double latitude;

    private Long distance;

    private Long duration;

    public Waypoint(){}

    public Waypoint(String address, double longitude, double latitude, Long distance, Long duration) {
        this.notes = "";
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.duration = duration;
    }

    public ObjectId getId() {return id;}

    public void setId(ObjectId id) {this.id = id;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}

    public double getLongitude() {return longitude;}

    public void setLongitude(double longitude) {this.longitude = longitude;}

    public double getLatitude() {return latitude;}

    public void setLatitude(double latitude) {this.latitude = latitude;}

    public Long getDistance() {return distance;}

    public void setDistance(Long distance) {this.distance = distance;}

    public Long getDuration() {return duration;}

    public void setDuration(Long duration) {this.duration = duration;}
}
