package com.example.forumpage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.forumpage.repositories.PostRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/{postId}")
    public String showPost(@PathVariable Long postId, Model model, HttpSession session){
        

        return "post";
    }

}
