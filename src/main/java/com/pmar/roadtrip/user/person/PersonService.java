package com.pmar.roadtrip.user.person;


import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.search.RepoSearchImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repo;


    public List<Person> getAll(){
        return repo.findAll();
    }

    public Person getById(String id){
        ObjectId oId = new ObjectId(id);
        return repo.findById(oId).orElseThrow(
                ()-> new IllegalArgumentException("ID: " + id + " does not exist")
        );
    }



}
