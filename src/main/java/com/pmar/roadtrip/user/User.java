package com.pmar.roadtrip.user;
import com.pmar.roadtrip.route.Route;

import java.util.ArrayList;

public interface User {


    String getFirstName();
    String getLastName();
    String getUsername();
    String getEmail();
    Long getAccountId();

    void setFirstName(String newName);
    void setLastName(String newLastName);
    void setUsername(String newUsername);



}
