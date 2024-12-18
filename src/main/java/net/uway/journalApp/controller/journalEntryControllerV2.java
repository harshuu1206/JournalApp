package net.uway.journalApp.controller;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.entity.User;
import net.uway.journalApp.services.JournalEntryService;
import net.uway.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Journal")
public class journalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
//    @GetMapping
//    public ResponseEntity<List<JournalEntry>> getAll() {
//        return ResponseEntity.ok(new ArrayList<>(journalEntries.values()));
//    }

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.FindByUserName(userName);
        List<JournalEntry> all= user.getJournalEntries();
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

       @PostMapping("{userName")
       public ResponseEntity<JournalEntry>   createEntry(@RequestBody JournalEntry myEntry, @PathVariable String UserName){
        try{
            journalEntryService.saveEntry(myEntry,UserName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
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

    @DeleteMapping("/{userName}/{myid}")
    public ResponseEntity<?> deleteJournalEntryByid(@PathVariable ObjectId myid, @PathVariable String userName){
        journalEntryService.deleteById(myid,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName]/{id}")
    public ResponseEntity<?> updateJournalByid(
            @PathVariable ObjectId myId
            , @RequestBody JournalEntry newEntry
            ,@PathVariable String userName
    ){
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old !=null){
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
