package com.pmar.roadtrip.waypoint;


import com.google.maps.model.DirectionsLeg;
import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.route.RouteService;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.user.person.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WaypointService {

    @Autowired
    WaypointRepository wpRepository;


    @Autowired
    PersonRepository pRepository;
    @Autowired
    PersonService pService;
    @Autowired
    MongoTemplate mongoTemplate;


    /*
    Each route, with or without waypoints, has an origin and a destination.
    The first waypoint (aka origin) does not store a distance or duration because
    that's where the user starts their trip. Each waypoint gets the origin added
    to the list where distance and duration are 0. Then the for loop goes through
    each leg and saves the i-1 -> i information at i
     */

    public List<Waypoint> createWaypoints(DirectionsLeg[] legs, ObjectId rId) {
        List<Waypoint> ret = new ArrayList<>();

        String oAddress = legs[0].startAddress;
        double oLng = legs[0].startLocation.lng;
        double oLat = legs[0].startLocation.lat;
        Long oDistance = 0L;
        Long oDuration = 0L;
        Waypoint oWaypoint = wpRepository.save(new Waypoint(oAddress,oLng,oLat,oDistance,oDuration));
        updateMongoDB(rId, oWaypoint);
        ret.add(oWaypoint);

        for (int i = 1;i<legs.length;i++){
            String address = legs[i].endAddress;
            double lng = legs[i].endLocation.lng;
            double lat = legs[i].endLocation.lat;
            Long distance = legs[i].distance.inMeters;
            Long duration = legs[i].duration.inSeconds;
            Waypoint waypoint = wpRepository.save(new Waypoint(address,lng,lat,distance,duration));
            updateMongoDB(rId, waypoint);
            ret.add(waypoint);
        }

        return ret;

    }

    public void updateMongoDB(ObjectId rId, Waypoint waypoint){
        mongoTemplate.update(Route.class)
                .matching(Criteria.where("_id").is(rId))
                .apply(new Update().push("waypoints").value(waypoint))
                .first();
    }

    public Waypoint getWaypoint(String id){
        return wpRepository.findById(new ObjectId(id)).orElseThrow(()-> new IllegalArgumentException("Waypoint id " + id + " not found"));
    }

    public Waypoint editNote(String id, String note){
        Waypoint waypoint = wpRepository.findById(new ObjectId(id)).orElseThrow(()-> new IllegalArgumentException("Waypoint id " + id + " not found"));
        waypoint.setNotes(note);
        return wpRepository.save(waypoint);
    }

    public String getNote(String id){
        Waypoint waypoint = wpRepository.findById(new ObjectId(id)).orElseThrow(()-> new IllegalArgumentException("Waypoint id " + id + " not found"));
        return waypoint.getNotes();
    }


    public void deleteWaypoint(String waypointId){
        wpRepository.deleteById(new ObjectId(waypointId));
    }

}
