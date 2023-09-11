package com.pmar.roadtrip.waypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class WaypointController {

    @Autowired
    WaypointService service;








    @PostMapping("/waypoint/delete")
    public void deleteWaypoint(@RequestBody Map<String,String> json){
        String userId = json.get("userId");
        String waypointId = json.get("waypointId");
        service.deleteWaypoint(userId,waypointId);
    }
}
