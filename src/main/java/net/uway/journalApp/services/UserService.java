package net.uway.journalApp.services;

import lombok.NonNull;
import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.entity.User;
import net.uway.journalApp.repository.JournalEntryRepository;
import net.uway.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

   @Autowired
    private UserRepository UserRepository;

   public void saveEntry(User user){
       UserRepository.save(user);
   }

   public List<User> getAll(){
       return UserRepository.findAll();
   }

   public Optional<User> findById(ObjectId id){ return UserRepository.findById(id);
   }

   public void deleteById(ObjectId id){UserRepository.deleteById(id);
   }

   public User FindByUserName(String username){
       return UserRepository.FindByUsername(username);
   }
}





