package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import com.pmar.roadtrip.request.DirectionsRequest;
import com.pmar.roadtrip.request.GeoContextBuilder;

import com.pmar.roadtrip.search.RepoSearch;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pmar.roadtrip.request.GeoContextBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    @Value("${env.API_KEY}")
    private String apiKey;

    @Autowired
    RouteRepository repo;
    @Autowired
    RepoSearch srepo;

    public List<Route> getRoutes(String id){
        List<ObjectId> routesId = srepo.findRoutesIdByUserId(new ObjectId(id));
        List<Route> ret = new ArrayList<>();
        for (ObjectId routeId: routesId){
            Route route = repo.findById(routeId).
                    orElseThrow(()-> new IllegalArgumentException("User ID: " + id + " does not exist"));
            ret.add(route);
        }

        return ret;
    }
	//TODO Find how to store a routes waypoints, if exists
//    public Map<String,String> requestRoute(Long userId,String origin, String destination) {
//
//		//TODO move lines 38-55 into another function - to allow the functionality of changing a route without waypoints/with waypoints
//		GeoContextBuilder gCB = new GeoContextBuilder();
//		GeoApiContext context =gCB.getContext();
//
//		DirectionsRequest dRequest = new DirectionsRequest(context,origin,destination);
//
//		DirectionsResult result = dRequest.request();
//		context.shutdown();
//
//		LatLng start = result.routes[0].legs[0].startLocation;
//		LatLng end = result.routes[0].legs[0].endLocation;
//
//		double startLat = start.lat;
//		double startLng = start.lng;
//		double endLat = end.lat;
//		double endLng = end.lng;
//
//		Long duration = result.routes[0].legs[0].duration.inSeconds;
//		Long distance = result.routes[0].legs[0].distance.inMeters;
//
//		Map<String, String> routeInfo = new HashMap<>();
//		routeInfo.put("origin",result.routes[0].legs[0].startAddress);
//		routeInfo.put("destination",result.routes[0].legs[0].endAddress);
//		routeInfo.put("startLat",Double.toString(startLat));
//		routeInfo.put("startLng",Double.toString(startLng));
//		routeInfo.put("endLat",Double.toString(endLat));
//		routeInfo.put("endLng",Double.toString(endLng));
//		routeInfo.put("duration",Long.toString(duration));
//		routeInfo.put("distance",Long.toString(distance));
//		return routeInfo;
//	}


}
