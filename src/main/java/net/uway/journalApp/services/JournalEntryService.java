package net.uway.journalApp.services;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

   @Autowired
    private JournalEntryRepository journalEntryRepository;

   public void saveEntry(JournalEntry JournalEntry){

       journalEntryRepository.save(JournalEntry);
   }

   public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
   }

   public Optional<JournalEntry> findById(ObjectId id) {
       return journalEntryRepository.findById(id);
   }

   public void deleteById(ObjectId id){
       journalEntryRepository.deleteById(id);
   }
}
