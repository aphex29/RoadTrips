package com.pmar.roadtrip.search;

import com.pmar.roadtrip.route.Route;
import org.bson.types.ObjectId;
import org.bson.Document;

import java.util.List;

public interface RepoSearch {
    List<ObjectId> findRoutesIdByUserId(ObjectId oid);
}
