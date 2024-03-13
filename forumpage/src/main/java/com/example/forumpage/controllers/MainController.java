package com.example.forumpage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.forumpage.model.Post;
import com.example.forumpage.repositories.PostRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public String homePage(Model model, HttpSession session){
        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "index";
    }

    @ModelAttribute("posts")
    public List<Post> postsList(){
        List<Post> posts = postRepository.findAll();
        
        for (Post post : posts) {
            System.out.println(post.getIdUsuario());
        }
        
        return posts;
    }
}
