package com.anoop.examples.data.repos;


import com.anoop.examples.data.entities.AlertEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface AlertRepository extends MongoRepository<AlertEntity, ObjectId> {
    List<AlertEntity> findByDeviceId(String deviceId);
}
