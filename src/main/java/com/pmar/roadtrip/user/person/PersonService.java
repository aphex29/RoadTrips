package com.pmar.roadtrip.user.person;
import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.route.RouteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;


    public Person addNewRoute(Long id,String origin, String destination){
        RouteService routeService = new RouteService();
        Route newRoute = routeService.getRouteInfo(origin, destination);
        Person person = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        person.getRoutes().add(newRoute);
        return repository.save(person);
    }

    public Person createPerson(String firstName, String lastName, String username, String email){
        Person person = new Person(firstName, lastName,username,email);
        return repository.save(person);
    }
}
