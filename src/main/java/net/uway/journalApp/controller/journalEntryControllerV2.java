package net.uway.journalApp.controller;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Journal")
public class journalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;
//    @GetMapping
//    public ResponseEntity<List<JournalEntry>> getAll() {
//        return ResponseEntity.ok(new ArrayList<>(journalEntries.values()));
//    }

    @GetMapping
    public List<JournalEntry> getAll(){ //localhost:8080/journal Get
        return journalEntryService.getAll();
    }

//    @PostMapping
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
//        journalEntries.put(myEntry.getId(), myEntry);
//        return ResponseEntity.status(HttpStatus.CREATED).body(myEntry);
//    }

       @PostMapping
       public JournalEntry createEntry(@RequestBody JournalEntry myEntry){//localhost:8080/journal Post
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
            return myEntry;
        }

//    @GetMapping("/{id}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long id) {
//        JournalEntry entry = journalEntries.get(id);
//        if (entry == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(entry);
//    }


        @GetMapping("id/{myid}")
        public JournalEntry getJournalEntryByid(@PathVariable Long myid){
            return null;
        }

    @DeleteMapping("/{myid}")
    public JournalEntry deleteJournalEntryByid(@PathVariable Long myid){
        return null;
    }


    @PutMapping("/{id}")
    public JournalEntry updateJournalByid(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return null;
    }


}
