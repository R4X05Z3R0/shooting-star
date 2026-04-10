package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shootingstar")
public class WishListController {
    private final WishListService service;

    @Autowired
    public WishListController(WishListService service){
        this.service = service;
    }

    @GetMapping("/homepage")
    public String homepage() {
        return "<h1>Ønskeskyen Clone: Online!</h1><p>The skeleton is alive.</p>";
    }

    @GetMapping("/wishes")
    public ResponseEntity<List<Wish>> wishes(){
        List<Wish> wishes = service.getAllWishes();
        return new ResponseEntity<>(wishes, HttpStatus.OK);
    }

}

