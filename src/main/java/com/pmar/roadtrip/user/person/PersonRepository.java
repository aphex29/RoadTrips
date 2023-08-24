package com.pmar.roadtrip.user.person;

import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, ObjectId> {
    Person findByUsernameAndPassword(String username,String password);
    Person findByUsername(String username);
}
