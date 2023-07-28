package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import com.pmar.roadtrip.request.DirectionsRequest;
import com.pmar.roadtrip.request.GeoContextBuilder;
import jakarta.persistence.EntityNotFoundException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pmar.roadtrip.request.GeoContextBuilder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    @Value("${env.API_KEY}")
    private String apiKey;

	@Autowired
	private RouteRepository repository;


    public Map<String,String> requestRoute(Long userId,String origin, String destination) {

		GeoContextBuilder gCB = new GeoContextBuilder();
		GeoApiContext context =gCB.getContext();


		DirectionsRequest dRequest = new DirectionsRequest(context,origin,destination);

		DirectionsResult result = dRequest.execute();
		context.shutdown();

		LatLng start = result.routes[0].legs[0].startLocation;
		LatLng end = result.routes[0].legs[0].endLocation;

		double startLat = start.lat;
		double startLng = start.lng;
		double endLat = end.lat;
		double endLng = end.lng;

		Long duration = result.routes[0].legs[0].duration.inSeconds;
		Long distance = result.routes[0].legs[0].distance.inMeters;

		Map<String, String> routeInfo = new HashMap<>();


		routeInfo.put("origin",result.routes[0].legs[0].startAddress);
		routeInfo.put("destination",result.routes[0].legs[0].endAddress);

		routeInfo.put("startLat",Double.toString(startLat));
		routeInfo.put("startLng",Double.toString(startLng));
		routeInfo.put("endLat",Double.toString(endLat));
		routeInfo.put("endLng",Double.toString(endLng));
		routeInfo.put("duration",Long.toString(duration));
		routeInfo.put("distance",Long.toString(distance));

		return routeInfo;
	}


	public Route getRoute(Long id){
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("RouteID: " + id + " not found"));
	}

	public List<Route> getRoutes(Long userId){
		return repository.findAllByUserId(userId)
				.orElseThrow(() -> new EntityNotFoundException("userId: " + userId + " not found"));
	}

	public Route editRoute(Long userId, Long routeId,String origin, String destination){
		Route oldRoute = getRoute(routeId);
		Map<String,String> routeVal = requestRoute(userId,origin,destination);

		String newOrigin = routeVal.get("origin");
		String newDestination = routeVal.get("destination");
		Long distance = Long.parseLong(routeVal.get("distance"));
		Long duration = Long.parseLong(routeVal.get("duration"));

		oldRoute.setOrigin(newOrigin);
		oldRoute.setDestination(newDestination);
		oldRoute.setDistance(distance);
		oldRoute.setDuration(duration);


		return repository.save(oldRoute);
	}

	public Route setRoute(Long userId,String origin, String destination){
		Map<String,String> routeInfo = requestRoute(userId,origin,destination);


		Long duration = Long.parseLong(routeInfo.get("duration"));
		Long distance= Long.parseLong(routeInfo.get("distance"));

		double startLat = Double.parseDouble(routeInfo.get("startLat"));
		double startLng = Double.parseDouble(routeInfo.get("startLng"));
		double endLat = Double.parseDouble(routeInfo.get("endLat"));
		double endLng = Double.parseDouble(routeInfo.get("endLng"));

		Route route = new Route(userId,origin,destination,distance,duration,startLat,startLng,endLat,endLng);
		return repository.save(route);
	}











}
