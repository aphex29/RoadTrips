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


    public List<Waypoint> createWaypoints(DirectionsLeg[] legs, ObjectId rId) {

        List<Waypoint> ret = new ArrayList<>();

        for (int i = 0;i<legs.length;i++){

            //To add origin into waypoints, where the origin is the only waypoint where "start" will be called
            if (i==0){
                String oAddress = legs[i].startAddress;
                double oLng = legs[i].startLocation.lng;
                double oLat = legs[i].startLocation.lat;
                Long oDistance = 0L;
                Long oDuration = 0L;
                Waypoint oWaypoint = wpRepository.save(new Waypoint(oAddress,oLng,oLat,oDistance,oDuration));
                mongoTemplate.update(Route.class)
                        .matching(Criteria.where("_id").is(rId))
                        .apply(new Update().push("waypoints").value(oWaypoint))
                        .first();
                ret.add(oWaypoint);
            }

            String address = legs[i].endAddress;
            double lng = legs[i].endLocation.lng;
            double lat = legs[i].endLocation.lat;
            Long distance = legs[i].distance.inMeters;
            Long duration = legs[i].duration.inSeconds;
            Waypoint waypoint = wpRepository.save(new Waypoint(address,lng,lat,distance,duration));

            mongoTemplate.update(Route.class)
                    .matching(Criteria.where("_id").is(rId))
                    .apply(new Update().push("waypoints").value(waypoint))
                    .first();
            ret.add(waypoint);
        }

        return ret;

    }



    public void deleteWaypoint(String userId, String waypointId){
        wpRepository.deleteById(new ObjectId(waypointId));
        Person p = pService.getById(userId);
        pRepository.save(p);
        //TODO
        //regenerateRoute (String... waypoints)
    }

}
