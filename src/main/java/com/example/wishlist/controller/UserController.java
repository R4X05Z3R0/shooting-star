package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shootingstar/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping()
    public int createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @GetMapping("/list")
    public List<User>getUsers(){
        return service.getAllUsers();
    }
}
