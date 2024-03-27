package com.example.forumpage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.forumpage.dto.PostDto;
import com.example.forumpage.model.Post;
import com.example.forumpage.repositories.PostRepository;
import com.example.forumpage.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/{postId}")
    public String showPost(@PathVariable Integer postId, Model model, HttpSession session){
        if(session.getAttribute("user") != null){
            model.addAttribute("sessionUser", session.getAttribute("user"));
        }
        Post post = postRepository.findPostById(postId);
        PostDto postDto = new PostDto();

        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());
        postDto.setCreationDate(post.getCreationDate());
        postDto.setUsername(userRepository.getUsername(post.getIdUsuario()));

        return "post";
    }

}
