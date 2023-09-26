package com.arisonreis.javacrud.controllers;


import com.arisonreis.javacrud.domain.user.RequestUser;
import com.arisonreis.javacrud.domain.user.User;
import com.arisonreis.javacrud.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity ListUsers() {
        var allUsers = repository.findAll();

        if(allUsers.isEmpty()){
            return ResponseEntity.ok("No many users created");
        }

        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    public ResponseEntity CreateUser(@RequestBody @Validated RequestUser data) {

        User newUser = new User(data);
        repository.save(newUser);

        return ResponseEntity.status(201).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity EditUser(@RequestBody RequestUser data){

        Optional<User> OptionalUser = repository.findById(data.id());

        if(OptionalUser.isPresent()){
            User user = OptionalUser.get();
            user.setName(data.name());
            user.setLastname(data.lastname());
            user.setAge(data.age());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeleteUser(@PathVariable String id){
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
