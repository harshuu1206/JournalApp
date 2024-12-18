package net.uway.journalApp.services;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.entity.User;
import net.uway.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

   @Autowired
    private JournalEntryRepository journalEntryRepository;

   @Autowired
   private UserService userService ;

   @Transactional
   public void saveEntry(JournalEntry JournalEntry, String userName){
       try{
           User user= userService.FindByUserName(userName);
           JournalEntry.setDate(LocalDateTime.now());
           JournalEntry saved=journalEntryRepository.save(JournalEntry);
           user.getJournalEntries().add(saved);
           user.setUsername(null);
           userService.saveEntry(user);
       }catch(Exception e){
           System.out.println(e);
           throw new RuntimeException("An error occured while saving the entry.",e);
       }
       User user= userService.FindByUserName(userName);
       JournalEntry.setDate(LocalDateTime.now());
       JournalEntry saved=journalEntryRepository.save(JournalEntry);
       user.getJournalEntries().add(saved);
       user.setUsername(null);
       userService.saveEntry(user);
   }

    public void saveEntry(JournalEntry JournalEntry){
        journalEntryRepository.save(JournalEntry);
    }

   public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
   }

   public Optional<JournalEntry> findById(ObjectId id) {
       return journalEntryRepository.findById(id);
   }

   public void deleteById(ObjectId id, String userName){
       User user= userService.FindByUserName(userName);
       user.getJournalEntries().removeIf(x -> x.getId().equals(id));
       userService.saveEntry(user);
       journalEntryRepository.deleteById(id);
   }
}
