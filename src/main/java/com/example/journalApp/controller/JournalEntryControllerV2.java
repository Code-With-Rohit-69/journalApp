package com.example.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    // get journal entry by id
    @GetMapping("/id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid) {
        return journalEntryService.getEntryById(myid);
    }

    // delete journal entry by id
    @DeleteMapping("/id/{myid}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myid) {
        journalEntryService.deleteById(myid);
        ;
        return true;
    }

    // update journal by id
    @PutMapping("/id/{myid}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) {

        JournalEntry old = journalEntryService.getEntryById(myid);

        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
                    : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                    : old.getContent());

            journalEntryService.saveEntry(old);
        }

        return old;
    }
}
