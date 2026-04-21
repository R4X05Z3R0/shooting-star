package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserService service;

    public AuthController(UserService service){
        this.service = service;
    }


    @PostMapping("/login")
     public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                         HttpSession session, Model model){

        try {
            User user = service.userLogin(username, password);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setMaxInactiveInterval(10*60);

        return "redirect:/wishlists/";

        } catch (ResponseStatusException e){
            model.addAttribute("wronglogin","Invalid Username/Password");
            return "login";
        }

    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/shootingstar/";
    }
}
