package com.example.forumpage.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.forumpage.dto.PostDto;
import com.example.forumpage.dto.UserDto;
import com.example.forumpage.model.Post;
import com.example.forumpage.model.User;
import com.example.forumpage.repositories.PostRepository;
import com.example.forumpage.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProfileController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/{userId}")
    public String showProfile(@PathVariable Integer userId, Model model, HttpSession session) {
        
        User profileUser = userRepository.findUserById(userId);
        UserDto userDto = new UserDto();

        userDto.setUsername(profileUser.getUsername());
        userDto.setCreationDate(userRepository.getCreationDateById(userId));
        model.addAttribute("sessionUser", session.getAttribute("user"));
        model.addAttribute("profileUser", userDto);
        model.addAttribute("postsQuantity", postRepository.countByUserId(userId));
        model.addAttribute("followers", userRepository.countByFollowedId(userId));
        model.addAttribute("followeds", userRepository.countByFollowerId(userId));

        if(session.getAttribute("user") != null){
            model.addAttribute("user", session.getAttribute("user"));
        }
        
        return "profile";
    }

    @ModelAttribute("posts")
    public List<PostDto> postsList(@PathVariable Integer userId){

        List<Post> posts = postRepository.findAllByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setTitle(post.getTitle());
            postDto.setCreationDate(post.getCreationDate());
            postDtos.add(postDto);
        }

        return postDtos;
    }
}
