package com.pmar.roadtrip.waypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class WaypointController {

    @Autowired
    WaypointService service;

    @GetMapping("/waypoint/{id}")
    public ResponseEntity<Waypoint> getWaypoint(@PathVariable String id){
        return new ResponseEntity<Waypoint>(service.getWaypoint(id),HttpStatus.OK);
    }

    @PostMapping("/waypoint/edit/note")
    public ResponseEntity<Waypoint> editNote(@RequestBody Map<String,String> json){
        String id = json.get("id");
        String note = json.get("note");
        return new ResponseEntity<Waypoint>(service.editNote(id,note), HttpStatus.OK);
    }

    @GetMapping("/waypoint/note/{id}")
    public ResponseEntity<String> getNote(@PathVariable String id){
        return new ResponseEntity<String>(service.getNote(id), HttpStatus.OK);
    }


    @PostMapping("/waypoint/delete")
    public void deleteWaypoint(@RequestBody Map<String,String> json){
        String waypointId = json.get("waypointId");
        service.deleteWaypoint(waypointId);
    }
}
