package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.pmar.roadtrip.request.DirectionsRequestImpl;
import com.pmar.roadtrip.request.GeoContextBuilder;

import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.user.person.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Value("${env.API_KEY}")
    private String apikey;

    @Autowired
    RouteRepository repo;

    @Autowired
    RepoSearch srepo;

    @Autowired
    MongoTemplate mongoTemplate;

    public Route createRoute(ObjectId userId, String origin, String destination){
        GeoContextBuilder gCB = new GeoContextBuilder();
        GeoApiContext.Builder builder =gCB.getBuilder();
        GeoApiContext context = builder.apiKey(apikey).build();

        DirectionsRequestImpl dRequest = new DirectionsRequestImpl(context,origin,destination);
        DirectionsResult result = dRequest.request();

        String startAdd = result.routes[0].legs[0].startAddress;
        String endAdd = result.routes[0].legs[0].endAddress;
        LatLng start = result.routes[0].legs[0].startLocation;
        LatLng end = result.routes[0].legs[0].endLocation;

        double startLat = start.lat;
        double startLng = start.lng;
        double endLat = end.lat;
        double endLng = end.lng;

        Long duration = result.routes[0].legs[0].duration.inSeconds;
        Long distance = result.routes[0].legs[0].distance.inMeters;

        Route route = repo.save(
                new Route(startAdd,endAdd,distance,duration,startLat,startLng,endLat,endLng)
        );

        mongoTemplate.update(Person.class)
                .matching(Criteria.where("_id").is(userId))
                .apply(new Update().push("routes").value(route))
                .first();

        return route;
    }

    public List<Route> getRoutes(ObjectId userId){
        List<ObjectId> routesId = srepo.findRoutesIdByUserId(userId);
        List<Route> ret = new ArrayList<>();
        for (ObjectId routeId: routesId){
            Route route = repo.findById(routeId).
                    orElseThrow(()-> new IllegalArgumentException("User ID: " + userId.toString() + " does not exist"));
            ret.add(route);
        }

        return ret;
    }

    public Route getRoute(ObjectId routeId){
        return repo.findById(routeId).orElseThrow(() -> new IllegalArgumentException("Route ID: " + routeId.toString() + " does not exist"));
    }


    public void deleteRoute(ObjectId routeId){
        repo.deleteById(routeId);
    }



}
