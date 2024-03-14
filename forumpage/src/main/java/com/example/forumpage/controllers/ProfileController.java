package com.example.forumpage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.forumpage.model.User;
import com.example.forumpage.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/{userId}")
    public String showProfile(@PathVariable Long userId, Model model, HttpSession session) {
        
        User profileUser = userRepository.findUserById(userId);
        model.addAttribute("profileUser", profileUser);
        
        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
        }

        return "profile";
    }
}
