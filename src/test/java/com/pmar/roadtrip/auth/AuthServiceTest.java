package com.pmar.roadtrip.auth;


import com.pmar.roadtrip.auth.AuthService;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.user.person.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;



    @Test
    public void verifyUserTest(){
        String username = "username";
        String password = "password";
        Person person = new Person("test","user","username","password","email@email.com");
        when(personRepository.findByUsernameAndPassword(username,password)).thenReturn(Optional.of(person));

        assertNotNull(authService.verifyUser(username,password));
    }


    @Test
    public void verifyNewAccTest(){
        String username = "username";
        String email = "email@email.com";
        when(personRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(personRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertTrue(authService.verifyNewAccount(username,email));
    }
}
