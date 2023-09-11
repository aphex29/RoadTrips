package com.pmar.roadtrip.user.person;


import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.search.RepoSearchImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired PersonRepository repo;

    @Autowired MongoTemplate mongoTemplate;

    public Person createPerson(String firstName, String lastName, String username, String password, String email){
        Person person = new Person(firstName,lastName,username, password, email);
        return repo.save(person);
    }

    public Person updateFirstName(ObjectId userId,String newFirstName){
        mongoTemplate.update(Person.class)
                .matching(Criteria.where("_id").is(userId))
                .apply(new Update().set("firstName",newFirstName))
                .first();
        return repo.findById(userId).orElseThrow(() -> new IllegalArgumentException("ID: " + userId + " does not exist"));
    }

    public Person updateLastName(ObjectId userId,String newLastName){
        mongoTemplate.update(Person.class)
                .matching(Criteria.where("_id").is(userId))
                .apply(new Update().set("firstName",newLastName))
                .first();
        return repo.findById(userId).orElseThrow(() -> new IllegalArgumentException("ID: " + userId + " does not exist"));
    }

    public Person updateUsername(ObjectId userId,String newUsername){
        mongoTemplate.update(Person.class)
                .matching(Criteria.where("_id").is(userId))
                .apply(new Update().set("firstName",newUsername))
                .first();
        return repo.findById(userId).orElseThrow(() -> new IllegalArgumentException("ID: " + userId + " does not exist"));
    }

    public Person updateEmail(ObjectId userId,String email){
        mongoTemplate.update(Person.class)
                .matching(Criteria.where("_id").is(userId))
                .apply(new Update().set("firstName",email))
                .first();
        return repo.findById(userId).orElseThrow(() -> new IllegalArgumentException("ID: " + userId + " does not exist"));
    }

    public List<Person> getAll(){
        return repo.findAll();
    }

    public Person getByUsername(String username){
        return repo.findByUsername(username).orElse(null);
    }

    public Person getByEmail(String email){
        return repo.findByEmail(email).orElse(null);
    }

    public Person getById(String id){
        return repo.findById(new ObjectId(id)).orElseThrow(() -> new IllegalArgumentException("ID: " + id + " does not exist"));
    }

    public Person getByUsernameAndPassword(String username, String password){
        return repo.findByUsernameAndPassword(username,password).orElseThrow(
                ()->new IllegalArgumentException("Incorrect credentials"));
    }

    public void deleteUser(ObjectId userId){
        repo.deleteById(userId);
    }



}
