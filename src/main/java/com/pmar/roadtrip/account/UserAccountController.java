package com.pmar.roadtrip.account;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/useraccount")
public class UserAccountController {

    @GetMapping("/get")
    public String test(){
        return "H111!!!!!";
    }


}
