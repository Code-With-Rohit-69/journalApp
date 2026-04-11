package com.example.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.journalApp.entity.UserEntry;


public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUsername(String username);
}
