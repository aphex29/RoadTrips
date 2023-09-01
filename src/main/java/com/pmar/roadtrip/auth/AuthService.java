package com.pmar.roadtrip.auth;

import com.pmar.roadtrip.user.User;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.user.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService personService;

    public Person verifyUser(String username, String password){
        return personService.getByUsernameAndPassword(username,password);
    }

    public Boolean verifyNewAccount(String username, String email){
        User user1 = personService.getByUsername(username);
        User user2 = personService.getByEmail(email);
        if (user1==null && user2==null) return true;
        else return false;
    }
}
