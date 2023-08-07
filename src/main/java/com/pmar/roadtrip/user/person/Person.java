package com.pmar.roadtrip.user.person;

import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.user.User;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;


@Document(collection="user")
public class Person implements User{

    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    @DocumentReference
    private List<Route> routes;

    public Person(){}
    public Person(String firstName, String lastName, String username,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.routes=new ArrayList<Route>();
    }


    @Override
    public String toString(){
        return String.format("Person[ID: %s, Name: %s %s, Username: %s, Email: %s]",id.toString(),firstName,lastName,username,email);
    }

    public ObjectId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Route> getRoutes(){
        return routes;
    }

    public void setFirstName(String newName) {
        firstName = newName;
    }

    public void setLastName(String newLastName) {
        lastName = newLastName;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

    public void setRoute(Route newRoute){
        routes.add(newRoute);
    }
}
