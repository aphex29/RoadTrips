package com.pmar.roadtrip;

import com.google.gson.JsonObject;
import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.user.person.Person;
import com.pmar.roadtrip.user.person.PersonRepository;
import com.pmar.roadtrip.user.person.PersonService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootApplication
@RestController
public class RoadTripsApplication {

	@Value("${env.API_KEY}")
	private String apiKey;

	public static void main(String[] args) {
		SpringApplication.run(RoadTripsApplication.class, args);

		System.out.println("THIS WORKS!!!");

	}

	@Bean
	CommandLineRunner commandLineRunner(PersonRepository personRepo, PersonService personService){
		return args -> {

			personRepo.deleteAll();

			Person person1 = new Person(1L,"Patrick","mark","aphex","markowski@gmail.com");
			personRepo.save(person1);

			Person person2 = new Person(2L,"Markwee","TEST2","prof","mail@gmail.com");
			personRepo.save(person2);



			System.out.println("*********Test1***********");
			System.out.println(personService.getPerson(1L));
			System.out.println(personService.getPerson(2L));
			System.out.println("*********End Test2***********");



		};
	}


}


