package com.example.journalApp.service;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.repository.JournalEntryRepo;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    // @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        UserEntry user = userEntryService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userEntryService.createUser(user);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        UserEntry user = userEntryService.findByUserName(username);
        user.getJournalEntries().removeIf((x) -> x.getId() == id);
        userEntryService.createUser(user);
        journalEntryRepo.deleteById(id);
    }

}
