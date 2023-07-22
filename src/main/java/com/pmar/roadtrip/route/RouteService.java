package com.pmar.roadtrip.route;

import jakarta.persistence.EntityNotFoundException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    @Value("${env.API_KEY}")
    private String apiKey;

	@Autowired
	private RouteRepository repository;


    public Map<String,String> createRouteInfo(Long userId,String origin, String destination){
        //Creating JSON payload to send to Googles API Server
        String bodyJson = 	String.format("""
							{
								"origin":	
									{
										"address": "%s"	         
									},							
								"destination":
								{
									"address": "%s"				
								},								
								"travelMode":"DRIVE",
							}
							""",origin,destination);

        //Sending post request
        HttpResponse<String> response = Unirest.post("https://routes.googleapis.com/directions/v2:computeRoutes")
                .header("Content-Type", "application/json")
                .header("X-Goog-Api-Key", apiKey)
                .header("X-Goog-FieldMask","routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline")
                .body(bodyJson)
                .asString();

		JSONObject jobject = new JSONObject(response);
		int jsonDistance = jobject.getJSONArray("routes").getJSONObject(0).getInt("distanceMeters");
		String secondsStr = jobject.getJSONArray("routes").getJSONObject(0).getString("duration");

		//Truncating excess decimals
		double distance = (jsonDistance*0.00062137)*100;
		distance = (double) ((int) distance);
		distance=distance/100;

		int time = Integer.parseInt(secondsStr.substring(0,secondsStr.length()));
		int hours = time/60;
		int minutes = time%60;

		Map<String,String> routeVal = new HashMap<String,String>();
		routeVal.put("userId",Long.toString(userId));
		routeVal.put("origin",origin);
		routeVal.put("destination",destination);
		routeVal.put("distance",Double.toString(distance));
		routeVal.put("hours",Integer.toString(hours));
		routeVal.put("minutes",Integer.toString(minutes));

		return routeVal;
    }


	public Route getRoute(Long routeId){
		return repository.findById(routeId)
				.orElseThrow(() -> new EntityNotFoundException("RouteID: " + routeId + " not found"));
	}

	public List<Route> getRoutes(Long userId){
		return repository.findAllByUserId(userId)
				.orElseThrow(() -> new EntityNotFoundException("userId: " + userId + " not found"));
	}

	public Route editRoute(Long userId, Long routeId,String origin, String destination){
		Route oldRoute = getRoute(routeId);
		Map<String,String> routeVal = createRouteInfo(userId,origin,destination);

		String newOrigin = routeVal.get("origin");
		String newDestination = routeVal.get("destination");
		double distance = Double.parseDouble(routeVal.get("distance"));
		int hours = Integer.parseInt(routeVal.get("hours"));
		int minutes = Integer.parseInt(routeVal.get("hours"));

		oldRoute.setOrigin(newOrigin);
		oldRoute.setDestination(newDestination);
		oldRoute.setDistance(distance);
		oldRoute.setHours(hours);
		oldRoute.setMinutes(minutes);

		return repository.save(oldRoute);
	}

	public Route setRoute(Long userId,String origin, String destination){
		Map<String,String> routeVal = createRouteInfo(userId,origin,destination);

		double distance = Double.parseDouble(routeVal.get("distance"));
		int hours = Integer.parseInt(routeVal.get("hours"));
		int minutes = Integer.parseInt(routeVal.get("minutes"));

		Route route = new Route(userId,origin,destination,distance,hours,minutes);
		return repository.save(route);
	}











}
