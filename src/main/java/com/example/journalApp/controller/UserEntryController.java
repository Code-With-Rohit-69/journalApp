package com.example.journalApp.controller;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.UserEntryService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<UserEntry> getAllUsers() {
        return userEntryService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(userEntryService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public void createUser(@RequestBody UserEntry user) {
        userEntryService.createUser(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry user) {
        UserEntry userInDb = userEntryService.findByUserName(user.getUsername());

        if (userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userEntryService.createUser(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
