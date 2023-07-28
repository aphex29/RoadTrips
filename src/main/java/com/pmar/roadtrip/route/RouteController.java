package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.pmar.roadtrip.request.DirectionsRequest;
import com.pmar.roadtrip.user.person.Person;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
public class RouteController {

    @Autowired
    private RouteService service;

    @GetMapping("/api/v1/get/route")
    public ResponseEntity<Route> getRoute(@RequestBody Map<String,String> json){
        Long routeId = Long.parseLong(json.get("routeId"));
        return new ResponseEntity<Route>(service.getRoute(routeId),HttpStatus.OK);
    }

    @GetMapping("/api/v1/get/routes")
    public ResponseEntity<List<Route>> getRoutes(@RequestBody Map<String,String> json){
        Long userId = Long.parseLong(json.get("userId"));
        return new ResponseEntity<List<Route>>(service.getRoutes(userId),HttpStatus.OK);
    }

    @PostMapping("api/v1/request/route")
    public ResponseEntity<Map<String,String>> requestRoute (@RequestBody Map<String,String> json){
        Long userId = Long.parseLong(json.get("userId"));
        String origin = json.get("origin");
        String destination = json.get("destination");
        return new ResponseEntity<Map<String,String>>(service.requestRoute(userId,origin,destination),HttpStatus.OK);
    }

    @PostMapping("api/v1/set/route")
    public ResponseEntity<Route> setRoute(@RequestBody Map<String,String> json){
        Long userId = Long.parseLong(json.get("userId"));
        String origin = json.get("origin");
        String destination = json.get("destination");
        return new ResponseEntity<Route>(service.setRoute(userId,origin,destination),HttpStatus.OK);
    }

    @PostMapping("api/v1/edit/route")
    public ResponseEntity<Route> editRoute(@RequestBody Map<String,String> json){
        Long userId = Long.parseLong(json.get("userId"));
        Long routeId = Long.parseLong(json.get("routeId"));
        String origin = json.get("origin");
        String destination = json.get("destination");
        return new ResponseEntity<Route>(service.editRoute(userId,routeId,origin,destination),HttpStatus.OK);
    }









}
