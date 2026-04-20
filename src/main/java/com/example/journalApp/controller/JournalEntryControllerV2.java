package com.example.journalApp.controller;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.JournalEntryService;
import com.example.journalApp.service.UserEntryService;

// controller -> services -> repository

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntityOfUser(@PathVariable String username) {
        UserEntry user = userEntryService.findByUserName(username);

        List<JournalEntry> list = user.getJournalEntries();

        if (list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ResponseEntity<>("No Journal Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        return new ResponseEntity<>(journalEntryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    // get journal entry by id
    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid) {
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myid);

        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // delete journal entry by id
    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid, @PathVariable String username) {
        journalEntryService.deleteById(myid, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update journal by id
    @PutMapping("/id/{username}/{myid}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myid, @PathVariable String username, @RequestBody JournalEntry newEntry) {
        Optional<JournalEntry> old = journalEntryService.getEntryById(myid);

        if (old.isPresent()) {
            JournalEntry existing = old.get();

            existing.setTitle(
                    newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                            ? newEntry.getTitle()
                            : existing.getTitle());

            existing.setContent(
                    newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                            ? newEntry.getContent()
                            : existing.getContent());

            journalEntryService.saveEntry(existing, username);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
