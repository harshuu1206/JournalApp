package net.uway.journalApp.controller;

import net.uway.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Journal")
public class journalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap();

    @GetMapping
    public List<JournalEntry> getAll(){ //localhost:8080/journal Get
        return new ArrayList<>(journalEntries.values());

    }

       @PostMapping
       public boolean createEntry(@RequestBody JournalEntry myEntry){ //localhost:8080/journal Post
           journalEntries.put(myEntry.getId(),myEntry);
           return true;
        }

        @GetMapping("/id{myid}")
        public JournalEntry getJournalEntryByid(@PathVariable Long myid){
          return  journalEntries.get(myid);
        }

    @DeleteMapping("/id{myid}")
    public JournalEntry deleteJournalEntryByid(@PathVariable Long myid){
        return  journalEntries.remove(myid);
    }


    @PutMapping("/id/{id}")
    public JournalEntry updateJournalByid(@PathVariable Long id, @RequestBody JournalEntry myEntry){
       return journalEntries.put(id, myEntry);
    }


}
