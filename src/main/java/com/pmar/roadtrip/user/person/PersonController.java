package com.pmar.roadtrip.user.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
public class PersonController {
    PersonService service;
    public PersonController(PersonService service){
        this.service = service;
    }



    @PostMapping("/api/v1/get/person")
    public ResponseEntity<Person> getPerson(@RequestBody Map<String,String> json){
        Long accountId = Long.parseLong(json.get("accountId"));
        return new ResponseEntity<Person>(service.getPerson(accountId),HttpStatus.OK);
    }

    @PostMapping("/api/v1/create/person")
    public ResponseEntity<Person> createPerson(@RequestBody Map<String,String> json){
        Long accountId = Long.parseLong(json.get("accountId"));
        String firstName = json.get("firstName");
        String lastName = json.get("lastName");
        String username = json.get("username");
        String email = json.get("email");

        return new ResponseEntity<Person>(service.createPerson(accountId,firstName, lastName, username, email), HttpStatus.OK);
    }


}
