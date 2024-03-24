package com.example.forumpage.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forumpage.dto.PostDto;
import com.example.forumpage.dto.UserDto;
import com.example.forumpage.model.Post;
import com.example.forumpage.model.User;
import com.example.forumpage.repositories.PostRepository;
import com.example.forumpage.repositories.UserRepository;
import com.example.forumpage.services.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class ProfileController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

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

        @PostMapping("/follow-user")
        public ResponseEntity<Object> followUser(@RequestParam Integer followedId, HttpSession session, HttpServletResponse response){
            User sessionUser = (User) session.getAttribute("user");
            if (sessionUser != null) {
                customUserDetailsService.followUser(followedId, sessionUser.getId());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
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
