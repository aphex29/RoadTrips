package com.pmar.roadtrip;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.gson.JsonObject;
import com.pmar.roadtrip.account.UserAccount;
import com.pmar.roadtrip.account.UserAccountRepository;
import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.route.RouteRepository;
import com.pmar.roadtrip.route.RouteService;
import com.pmar.roadtrip.search.RepoSearch;
import com.pmar.roadtrip.search.RepoSearchImpl;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.user.person.PersonService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootApplication
@RestController
@ComponentScan
public class RoadTripsApplication {

	@Value("${env.API_KEY}")
	private String apiKey;

	public static void main(String[] args) {
		SpringApplication.run(RoadTripsApplication.class, args);

		System.out.println("THIS WORKS!!!");

	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer(){
		return builder -> builder.serializerByType(ObjectId.class,new ToStringSerializer());
	}



	@Bean
	CommandLineRunner commandLineRunner(PersonRepository personRepository, PersonService personService, RouteRepository routeRepository, RepoSearch repoSearch, RouteService routeService){
		return args -> {
			personRepository.deleteAll();
			routeRepository.deleteAll();
//
			Person p1 = new Person("pat","mar","aphex","123","email@emai.com");
			Person p2 = new Person("random","random2","random3", "345","random4@email.com");
			personRepository.save(p1);
			personRepository.save(p2);

			Route r1p1 = new Route("chicago","springfield",12.50,6700L,25.76,-30.56,-60.756,-50.00);
			Route r2p1 = new Route("chicago","Los Angeles",2004.50,530000L,212.76,-31234.56,-612.756,-1220.00);

			routeRepository.save(r1p1);
			routeRepository.save(r2p1);

			p1.setRoute(r1p1);
			p1.setRoute(r2p1);
//
//			Person p3 = new Person("123","rando2354m2","rand346om3","rando678567m4");
//			personRepository.save(p3);
//
//			Route r3p1 = new Route("chicago","springfield",12.50,6700L,25.76,-30.56,-60.756,-50.00);
//
//
//
//
//			personRepository.save(p1);
//
////			System.out.println(routeService.getRoutes("64cdbbce13906a3f60722c4e"));
//
//			Person p4 = new Person("patrick","markowski","profoxlong","email@email.com");
//
//			personRepository.save(p4);
//
//			Route r2 = new Route("chicago","springfield",12.50,6700L,25.76,-30.56,-60.756,-50.00);
//			Route r3 = new Route("san francisco","washington",12.50,6700L,25.76,-30.56,-60.756,-50.00);
//
//			routeRepository.save(r2);
//			routeRepository.save(r3);
//
//			routeService.test(r2,new ObjectId("64d2ade14cb86741dc6d3528"));
//			routeService.test(r3,new ObjectId("64d2ade14cb86741dc6d3528"));


		};
	}


}


