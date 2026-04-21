package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shootingstar")
public class PageController {

    @GetMapping("/")
    public String homepage(HttpSession session){
        session.invalidate();
        return "homepage";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}