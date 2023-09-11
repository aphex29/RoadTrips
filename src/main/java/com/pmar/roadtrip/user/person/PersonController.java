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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired PersonService service;

    @GetMapping("/users")
    public ResponseEntity<List<Person>> getAll(){
        return new ResponseEntity<List<Person>>(service.getAll(), HttpStatus.OK);
    }



    @PostMapping("/users")
    public ResponseEntity<Person> createPerson(@RequestBody Map<String,String> json){
        String firstName = json.get("firstName");
        String lastName = json.get("lastName");
        String username = json.get("username");
        String password = json.get("password");
        String email = json.get("email");
        return new ResponseEntity<Person>(service.createPerson(firstName,lastName,username,password,email),HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") String id){
        return new ResponseEntity<Person>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("/update/firstName")
    public ResponseEntity<Person> updateFirstName(@RequestBody Map<String,String> json){
        String userId = json.get("userId");
        String firstName = json.get("firstName");
        return new ResponseEntity<Person>(service.updateFirstName(new ObjectId(userId),firstName),HttpStatus.OK);
    }

    @PostMapping("/update/lastName")
    public ResponseEntity<Person> updateLastName(@RequestBody Map<String,String> json){
        String userId = json.get("userId");
        String lastName = json.get("lastName");
        return new ResponseEntity<Person>(service.updateLastName(new ObjectId(userId),lastName),HttpStatus.OK);
    }

    @PostMapping("/update/username")
    public ResponseEntity<Person> updateUsername(@RequestBody Map<String,String> json){
        String userId = json.get("userId");
        String username = json.get("username");
        return new ResponseEntity<Person>(service.updateUsername(new ObjectId(userId),username),HttpStatus.OK);
    }

    @PostMapping("/update/email")
    public ResponseEntity<Person> updateEmail(@RequestBody Map<String,String> json){
        String userId = json.get("userId");
        String email = json.get("email");
        return new ResponseEntity<Person>(service.updateEmail(new ObjectId(userId),email),HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        service.deleteUser(new ObjectId(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
