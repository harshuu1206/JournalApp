package net.uway.journalApp.controller;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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
    public ResponseEntity<?> getAll(){ //localhost:8080/journal Get
        List<JournalEntry> all= journalEntryService.getAll();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
//        journalEntries.put(myEntry.getId(), myEntry);
//        return ResponseEntity.status(HttpStatus.CREATED).body(myEntry);
//    }

       @PostMapping
       public ResponseEntity<JournalEntry>   createEntry(@RequestBody JournalEntry myEntry){//localhost:8080/journal Post
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
         }

        }

//    @GetMapping("/{id}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long id) {
//        JournalEntry entry = journalEntries.get(id);
//        if (entry == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(entry);
//    }


        @GetMapping("/{myid}")
        public ResponseEntity<JournalEntry> getJournalEntryByid(@PathVariable ObjectId myid) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
               }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    @DeleteMapping("/{myid}")
    public ResponseEntity<?> deleteJournalEntryByid(@PathVariable ObjectId myid){
        journalEntryService.deleteById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJournalByid(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old !=null){
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
