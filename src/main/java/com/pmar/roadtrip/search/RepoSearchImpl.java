package com.pmar.roadtrip.search;

import com.google.common.collect.Lists;
import com.mongodb.client.*;
import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.user.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import org.bson.Document;
import org.bson.types.ObjectId;

@Component
public class RepoSearchImpl implements RepoSearch{

    @Autowired
    MongoClient client;



    @Override
    public List<ObjectId> findRoutesIdByUserId(ObjectId uid) {
        MongoDatabase database = client.getDatabase("RoadTrips");
        MongoCollection<Document> userCollection = database.getCollection("user");


        AggregateIterable<Document> userResult = userCollection.aggregate(Arrays.asList( new Document("$match",
                                                    new Document("_id", uid)),

                                                new Document("$unwind",
                                                        new Document("path", "$routes")),

                                                new Document("$project",
                                                    new Document("routeId", "$routes"))));

        List<ObjectId> routesId = new ArrayList<>();
        MongoCursor<Document> userIterator = userResult.iterator();

        while (userIterator.hasNext()){
            Document currRoute = userIterator.next();
            ObjectId id  = new ObjectId(
                                String.valueOf(
                                        currRoute.get("routeId")));
            routesId.add(id);
        }
        return routesId;
    }

    @Override
    public Boolean routesEmpty(ObjectId userId){
        MongoDatabase database = client.getDatabase("RoadTrips");
        MongoCollection<Document> userCollection = database.getCollection("user");

        FindIterable<Document> userResult=userCollection.find(new Document("$and", Arrays.asList(new Document("routes",
                        new Document("$size", 0L)),
                        new Document("_id", userId))));


        MongoCursor<Document> userResultIter = userResult.iterator();
        int size=0;
        while(userResultIter.hasNext()){
            Document currResult = userResultIter.next();
            size = String.valueOf(currResult.get("routes")).length()-2;
        }
        return size==0;
    }

}
