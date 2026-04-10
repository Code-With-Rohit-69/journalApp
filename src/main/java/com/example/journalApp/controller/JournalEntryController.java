// package com.example.journalApp.controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.RestController;

// import com.example.journalApp.entity.JournalEntry;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @RestController
// @RequestMapping("/_journal")
// public class JournalEntryController {

//     public Map<Long, JournalEntry> journalEntries = new HashMap<>();

//     @GetMapping
//     public List<JournalEntry> getAll() {
//         return new ArrayList<>(journalEntries.values());
//     }

//     @PostMapping
//     public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
//         journalEntries.put(myEntry.getId(), myEntry);
//         return myEntry;
//     }

//     // get journal entry by id
//     @GetMapping("/id/{myid}")
//     public JournalEntry getJournalEntryById(@PathVariable Long myid) {
//         if (!journalEntries.containsKey(myid)) {
//             return null;
//         }
//         JournalEntry entry = journalEntries.get(myid);
//         return entry;
//     }

//     // delete journal entry by id
//     @DeleteMapping("/id/{myid}")
//     public String deleteJournalEntryById(@PathVariable Long myid) {
//         if (!journalEntries.containsKey(myid))
//             return "Journal Not Exists. ❌";

//         journalEntries.remove(myid);

//         return "✅ Deleted";

//     }

//     // update journal by id
//     @PutMapping("/id/{myid}")
//     public JournalEntry updateJournalEntryById(@PathVariable Long myid, @RequestBody JournalEntry entity) {
//         if (!journalEntries.containsKey(myid))
//             return null;

//         journalEntries.put(myid, entity);

//         return entity;

//     }

// }