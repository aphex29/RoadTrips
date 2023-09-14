package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.pmar.roadtrip.request.DirectionsRequest;
import com.pmar.roadtrip.request.NoWaypointRequest;
import com.pmar.roadtrip.request.GeoContextBuilder;

import com.pmar.roadtrip.request.WaypointRequest;
import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.user.person.PersonService;
import com.pmar.roadtrip.waypoint.Waypoint;
import com.pmar.roadtrip.waypoint.WaypointService;
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
    @Autowired RouteRepository repo;
    @Autowired WaypointService waypointService;
    @Autowired RepoSearch srepo;
    @Autowired PersonService pService;
    @Autowired PersonRepository pRepo;
    @Autowired MongoTemplate mongoTemplate;


    public static GeoApiContext createContext(String key){
        GeoContextBuilder gCB = new GeoContextBuilder();
        GeoApiContext.Builder builder =gCB.getBuilder();
        return builder.apiKey(key).build();
    }


    public Route createRoute(ObjectId userId, String origin, String destination, String... waypoints){
        GeoApiContext context = RouteService.createContext(apikey);

        DirectionsRequest dRequest;
        DirectionsResult result;
        if (waypoints==null){
            dRequest = new NoWaypointRequest(context,origin,destination);
        }
        else{
            dRequest = new WaypointRequest(context,origin,destination, waypoints);
        }
        result = dRequest.request();

        Person person = pRepo.findById(userId).orElseThrow(()->new IllegalArgumentException("UserID " + userId + " does not exist"));

        String polyline = result.routes[0].overviewPolyline.getEncodedPath();
        Route route = repo.save(new Route(polyline));
        mongoTemplate.update(Person.class)
                .matching(Criteria.where("_id").is(userId))
                .apply(new Update().push("routes").value(route))
                .first();


        DirectionsLeg[] legs = result.routes[0].legs;
        List<Waypoint> list= waypointService.createWaypoints(legs,route.getId());

        for (Waypoint waypoint:list) route.setWaypoints(waypoint);
        person.setRoute(route);
        repo.save(route);
        pRepo.save(person);
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
        return repo.findById(routeId).orElseThrow(() -> new IllegalArgumentException("Route ID: " + routeId + " does not exist"));
    }


    public void deleteRoute(ObjectId routeId, String userId){
        //Get user and delete users route by id from their route list
        Person person = pRepo.findById(new ObjectId(userId)).orElseThrow(() -> new IllegalArgumentException("User ID: " + userId + " does not exist"));
        person.getRoutes().removeIf(route -> route.getId().equals(routeId));

        //Get route and delete all waypoints associated with that route from database
        Route route = repo.findById(routeId).orElseThrow(() -> new IllegalArgumentException("Route ID: " + routeId + " does not exist"));
        List<Waypoint> waypoints = route.getWaypoints();
        for (Waypoint waypoint:waypoints){
            waypointService.deleteWaypoint(waypoint.getId().toString());
        }
        repo.deleteById(routeId);
        pRepo.save(person);
    }



}
