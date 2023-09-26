package com.pmar.roadtrip.route;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.pmar.roadtrip.request.DirectionsRequest;
import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.waypoint.WaypointService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RouteServiceTest {

    @Value("${env.API_KEY}")
    private String apikey;

    @Autowired
    private RouteService routeService;

    @MockBean
    private RouteRepository routeRepository;

    @MockBean
    private RepoSearch repoSearch;


    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private WaypointService waypointService;

    @Mock
    private GeoApiContext context;

    @Mock
    DirectionsRequest request;

    @Mock
    private DirectionsResult result;

    @Captor
    private ArgumentCaptor<Route> routeCaptor;



    @Test
    public void getRoutesTest(){
        when(routeRepository.findAll()).thenReturn(Stream
                    .of(new Route("E34g@gh34@3431@gfgji@s354"),
                        new Route("24345@grdbf@ghGFg@svd"))
                    .collect(Collectors.toList()));

        assertEquals(2,routeService.getAll().size());
    }

    @Test
    public void getUsersRoutesTest(){
        ObjectId[] id = new ObjectId[]{new ObjectId("6502c8db2c09b959910cfe21"),
                                        new ObjectId("6502c8db2c09b959910cfe32"),
                                        new ObjectId("6502c8db2c09b959910cfe65")};

        List<ObjectId> idList = Arrays.asList(id);
        when(repoSearch.findRoutesIdByUserId(new ObjectId("6502c8db2c09b959910cfe95"))).thenReturn(idList);
        when(routeRepository.findById(id[0])).thenReturn(Optional.of(new Route("E34g@gh34@3431@gfgji@s354")));
        when(routeRepository.findById(id[1])).thenReturn(Optional.of(new Route("24345@grdbf@ghGFg@svd")));
        when(routeRepository.findById(id[2])).thenReturn(Optional.of(new Route("2sdf5@grdbf@gdfgFgsdfsa3@svd")));
        List<Route> routeList = routeService.getRoutes(new ObjectId("6502c8db2c09b959910cfe95"));

        assertEquals(3,routeList.size());
    }


    @Test
    public void createRouteWithNoWaypointsTest() {
        ObjectId userId = new ObjectId("6502c8db2c09b959910cfe95");
        String origin = "Chicago,IL";
        String destination = "Milwaukee, WI";
        String polyline = "E34g@gh34@3431@gfgji@s354";

        when(personRepository.findById(userId)).thenReturn(Optional.of(new Person("test","user","username","password","email@email.com")));

        Route route = new Route("E34g@gh34@3431@gfgji@s354");
        ObjectId rId = new ObjectId("6502c8db2c09b959910cfe21");

        when(routeRepository.save(route)).thenReturn(route);
        when(route.getId()).thenReturn(rId);
        String routePoly = routeService.createRoute(userId, origin, destination).getPolyline();

        assertEquals("E34g@gh34@3431@gfgji@s354",routePoly);
    }



    @Test
    public void getRouteNonexistentIdTest() {
        ObjectId routeId = new ObjectId();

        when(routeRepository.findById(routeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> routeService.getRoute(routeId));
    }

    @Test
    public void deleteRouteWithNonexistentUserIdTest() {
        ObjectId routeId = new ObjectId("6502c8db2c09b959910cfe95");
        String userId = "6502c8db2c09b959910cfe92";
        when(personRepository.findById(new ObjectId(userId))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> routeService.deleteRoute(routeId, userId));
    }

    @Test
    public void deleteRouteWithNonexistentRouteIdTest() {
        ObjectId routeId = new ObjectId("6502c8db2c09b959910cfe95");
        String userId = "6502c8db2c09b959910cfe92";
        when(personRepository.findById(new ObjectId(userId)))
                .thenReturn(Optional
                .of(new Person("test","user","username","password","email@email.com")));
        when(routeRepository.findById(routeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> routeService.deleteRoute(routeId, userId));
    }

    @Test
    public void getRoutesWithNonexistentUserIdTest() {
        ObjectId userId = new ObjectId();
        when(repoSearch.findRoutesIdByUserId(userId)).thenReturn(new ArrayList<>());
        List<Route> routes = routeService.getRoutes(userId);

        assertTrue(routes.isEmpty());
    }


}
