package com.pmar.roadtrip.route;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class RouteController {

    @Autowired
    RouteService service;

    @GetMapping("/routes/{userId}")
    public ResponseEntity<List<Route>> getRoutes(@PathVariable String userId){
        ObjectId uid = new ObjectId(userId);
        return new ResponseEntity<List<Route>>(service.getRoutes(uid),HttpStatus.OK);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<Route> getRoute(@PathVariable String routeId){
        ObjectId uid = new ObjectId(routeId);
        return new ResponseEntity<Route>(service.getRoute(uid),HttpStatus.OK);
    }

    @PostMapping("/route")
    public ResponseEntity<Route> createRoute(@RequestBody Map<String,String> json){
        ObjectId userId = new ObjectId(json.get("userId"));
        String origin = json.get("origin");
        String destination = json.get("destination");
        return new ResponseEntity<Route>(service.createRoute(userId,origin,destination),HttpStatus.OK);
    }

    @DeleteMapping("/route/delete/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String id){
        service.deleteRoute(new ObjectId(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
