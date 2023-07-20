package com.pmar.roadtrip.user;
import com.pmar.roadtrip.route.Route;

import java.util.ArrayList;

public interface User {


    String getFirstName();
    String getLastName();

    ArrayList<Route> getRoutes();

    void setFirstName(String newName);

    void setLastName(String newLastName);

    void setNewRoute(Route newRoute);

}
