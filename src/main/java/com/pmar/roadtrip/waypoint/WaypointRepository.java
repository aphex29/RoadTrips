package com.pmar.roadtrip.waypoint;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WaypointRepository extends MongoRepository<Waypoint, ObjectId>{


}
