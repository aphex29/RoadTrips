package com.pmar.roadtrip.route;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Value("${env.API_KEY}")
    private String apiKey;




    public Route getRouteInfo(String origin, String destination){
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
		int seconds = time%60;

		return new Route(origin,destination,distance,hours,seconds);

    }





}
