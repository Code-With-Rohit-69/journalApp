package com.example.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.UserEntryRepo;

@Service
public class UserEntryService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    // create user
    public void createUser(UserEntry user) {
        userEntryRepo.save(user);
    }

    // get All Users

    public List<UserEntry> getAllUsers() {
        return userEntryRepo.findAll();
    }

    // get user by id

    public UserEntry getUserById(ObjectId id) {
        return userEntryRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // delete user by id

    public void deleteUserById(ObjectId id) {
        userEntryRepo.deleteById(id);
    }

    // update user

    public UserEntry findByUserName(String userName) {
        return userEntryRepo.findByUsername(userName);
    }

}


