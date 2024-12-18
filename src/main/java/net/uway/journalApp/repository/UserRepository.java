package net.uway.journalApp.repository;

import net.uway.journalApp.entity.JournalEntry;
import net.uway.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{

    User FindByUsername(String username);

}
