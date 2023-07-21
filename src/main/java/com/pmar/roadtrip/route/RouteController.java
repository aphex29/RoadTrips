package com.pmar.roadtrip.route;

import com.pmar.roadtrip.user.person.Person;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RouteController {
    RouteService service;

    @Autowired
    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/api/v1/calc/route")
    public ResponseEntity<Route> calculateRoute(@RequestBody Map<String,String> json){
        Long userId = Long.parseLong(json.get("accountId"));
        String origin = json.get("origin");
        String destination = json.get("destination");
        return new ResponseEntity<Route>(service.calculateRoute(userId,origin,destination), HttpStatus.OK);
    }

    @PostMapping("/api/v1/get/route")
    public HttpEntity<Route> getRoute(@RequestBody Map<String,String> json){
        Long routeId = Long.parseLong(json.get("routeId"));
        return new ResponseEntity<Route>(service.getRoute(routeId),HttpStatus.OK);
    }

    @PostMapping("/api/v1/get/routes")
    public HttpEntity<List<Route>> getRoutes(@RequestBody Map<String,String> json){
        Long userId = Long.parseLong(json.get("userId"));
        return new ResponseEntity<List<Route>>(service.getRoutes(userId),HttpStatus.OK);
    }






}
