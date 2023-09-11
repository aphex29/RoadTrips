package com.pmar.roadtrip.FormatData;

import org.bson.types.ObjectId;

public class RequestObject {
    private ObjectId userId;
    private String origin;
    private String destination;
    private String[] waypoints;



    public RequestObject(){}
    public RequestObject(ObjectId userId, String origin, String destination, String[] waypoints) {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.waypoints = waypoints;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String[] waypoints) {
        this.waypoints = waypoints;
    }
}
