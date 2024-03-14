package com.example.forumpage.controllers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.forumpage.dto.PostDto;
import com.example.forumpage.model.Post;
import com.example.forumpage.repositories.PostRepository;
import com.example.forumpage.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String homePage(Model model, HttpSession session){
        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "index";
    }

    @ModelAttribute("posts")
    public List<PostDto> postsList(){
        
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        
        for (Post post : posts) {
            PostDto postDto = new PostDto();
            postDto.setTitle(post.getTitle());
            postDto.setBody(post.getBody());
            postDto.setIdUser(post.getIdUsuario());
            postDto.setUsername(userRepository.getUsername(post.getIdUsuario()));
            postDto.setCreationDate(post.getCreationDate());
            postDtos.add(postDto);
        }

        return postDtos;
    }
}
