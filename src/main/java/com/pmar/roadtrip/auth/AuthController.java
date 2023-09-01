package com.pmar.roadtrip.auth;


import com.pmar.roadtrip.user.User;
import com.pmar.roadtrip.user.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/verify")
    public User verifyUser(@RequestBody Map<String,String> json){
        String username = json.get("username");
        String password = json.get("password");
        User user = authService.verifyUser(username,password);
        if (user==null) return null;
        else return user;
    }

    @PostMapping("/verify/creation")
    public Boolean verifyNewAccount(@RequestBody Map<String,String> json){
        String username = json.get("username");
        String email = json.get("email");
        return authService.verifyNewAccount(username,email);
    }




}
