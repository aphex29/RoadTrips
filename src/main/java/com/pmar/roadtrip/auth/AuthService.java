package com.pmar.roadtrip.auth;

import com.pmar.roadtrip.user.User;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PersonRepository repository;

    public Person verifyUser(String username, String password){
        return repository.findByUsernameAndPassword(username,password);
    }
}
