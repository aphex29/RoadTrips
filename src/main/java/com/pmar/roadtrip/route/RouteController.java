package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.pmar.roadtrip.request.DirectionsRequest;
import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.user.person.Person;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {

    @Autowired
    RouteService service;



    @GetMapping("/get/routes/{id}")
    public ResponseEntity<List<Route>> getRoutes(@PathVariable String id){
        return new ResponseEntity<List<Route>>(service.getRoutes(id),HttpStatus.OK);
    }

}
