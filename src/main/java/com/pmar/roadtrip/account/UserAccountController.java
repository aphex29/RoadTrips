package com.pmar.roadtrip.account;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/useraccount")
public class UserAccountController {

    @GetMapping("/get")
    public String test(){
        return "H111!!!!!";
    }


}
