package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shootingstar/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createUser(@RequestBody User user){
        Integer createdUser = service.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<User>getUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Integer> updateUser(@PathVariable int id, @RequestBody User user){
        Integer updatedUser = service.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id){
        Integer deletedUser = service.deleteUser(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.NO_CONTENT);
    }
}
