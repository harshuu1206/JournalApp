package net.uway.journalApp.controller;


import net.uway.journalApp.entity.User;
import net.uway.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllusers(){
       return userService.getAll();
    }

    @PostMapping
    public void  createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName")
    public ResponseEntity<?> updateuser(@RequestBody User user,@PathVariable String userName){
     User userInDb = userService.FindByUserName(userName);
     if(userInDb !=null){
         userInDb.setUsername(user.getUsername());
         userInDb.setPasseord(user.getPasseord());
         userService.saveEntry(userInDb);
     }
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
