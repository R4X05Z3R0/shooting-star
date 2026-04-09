package com.example.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shootingstar")
public class WishListController {

    @GetMapping("/homepage")
    public String homepage() {
        return "<h1>Ønskeskyen Clone: Online!</h1><p>The skeleton is alive.</p>";
    }

}

