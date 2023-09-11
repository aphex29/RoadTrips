package com.pmar.roadtrip.route;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmar.roadtrip.FormatData.RequestObject;
import com.pmar.roadtrip.FormatData.StringToRequestObject;
import com.pmar.roadtrip.waypoint.Waypoint;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class RouteController {

    @Autowired RouteService service;

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
    public ResponseEntity<Route> createRoute(@RequestBody String json){
        RequestObject obj = StringToRequestObject.mapToObject(json);
        return new ResponseEntity<Route>(service.createRoute(
                obj.getUserId(),
                obj.getOrigin(),
                obj.getDestination(),
                obj.getWaypoints()
        ),HttpStatus.OK);


    }

    @PostMapping("/route/delete")
    public ResponseEntity<Void> deleteRoute(@RequestBody Map<String,String> json){
        service.deleteRoute(new ObjectId(json.get("routeId")), json.get("userId"));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
