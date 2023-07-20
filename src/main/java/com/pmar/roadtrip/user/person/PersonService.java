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

    public Person getPerson(Long accountId){
        return repository.findByAccountId(accountId)
                .orElseThrow(()-> new EntityNotFoundException("Account ID: "+ accountId + " not found"));
    }

    public Person createPerson(Long accountId,String firstName, String lastName, String username, String email){
        Person person = new Person(accountId, firstName, lastName,username,email);
        return repository.save(person);
    }
}
