package com.pmar.roadtrip.user;
import com.pmar.roadtrip.route.Route;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public interface User {


    ObjectId getId();
    String getFirstName();
    String getLastName();
    String getUsername();
    String getEmail();
    List<Route> getRoutes();

    void setFirstName(String newName);
    void setLastName(String newLastName);
    void setUsername(String newUsername);
    void setRoute(Route newRoute);



}
