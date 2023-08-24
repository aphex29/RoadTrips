package com.pmar.roadtrip.auth;


import com.pmar.roadtrip.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/verify")
    public User verifyUser(@RequestBody Map<String,String> json){
        String username = json.get("username");
        String password = json.get("password");
        User user = service.verifyUser(username,password);
        if (user.equals(null)) return null;
        else return user;
    }




}
