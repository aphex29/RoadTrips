package com.pmar.roadtrip.user.person;

import com.pmar.roadtrip.route.Route;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class PersonController {

    @Autowired
    PersonService service;


    @GetMapping("/get/all")
    public ResponseEntity<List<Person>> getAll(){
        return new ResponseEntity<List<Person>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") String id){
        return new ResponseEntity<Person>(service.getById(id),HttpStatus.OK);
    }


}
