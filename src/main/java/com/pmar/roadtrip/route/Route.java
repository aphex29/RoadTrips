package com.pmar.roadtrip.route;

import com.google.maps.model.LatLng;

import com.pmar.roadtrip.waypoint.Waypoint;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;


@Document(collection="route")
public class Route {

    @Id
    private ObjectId id;

    private List<Waypoint> waypoints;

    private String polyline;

    public Route(){}

    public Route(String polyline) {
        this.polyline = polyline;
        this.waypoints = new ArrayList<Waypoint>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Waypoint waypoint) {
        waypoints.add(waypoint);
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }
}
