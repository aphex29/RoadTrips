package com.pmar.roadtrip.search;

import com.mongodb.client.*;
import com.pmar.roadtrip.route.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.bson.Document;
import org.bson.types.ObjectId;

@Component
public class RepoSearchImpl implements RepoSearch{

    @Autowired
    MongoClient client;



    @Override
    public List<ObjectId> findRoutesIdByUserId(ObjectId oid) {
        MongoDatabase database = client.getDatabase("RoadTrips");
        MongoCollection<Document> userCollection = database.getCollection("user");


        AggregateIterable<Document> userResult = userCollection.aggregate(Arrays.asList( new Document("$match",
                                                    new Document("_id", oid)),

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
}
