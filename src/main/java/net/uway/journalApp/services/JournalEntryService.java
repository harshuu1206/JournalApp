package net.uway.journalApp.services;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
