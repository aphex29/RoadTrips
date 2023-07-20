package com.pmar.roadtrip.route;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    RouteService service;

    @Autowired
    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/api/route")
    public void getRouteInfo(@RequestParam(value="origin") String origin,
                             @RequestParam(value="destination") String destination){
		service.getRouteInfo(origin,destination);
    }



}
