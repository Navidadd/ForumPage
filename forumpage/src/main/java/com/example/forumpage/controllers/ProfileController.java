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
        
        if(session.getAttribute("user") != null){
            model.addAttribute("sessionUser", session.getAttribute("user"));
        }

        User profileUser = userRepository.findUserById(userId);
        UserDto userDto = new UserDto();

        userDto.setUsername(profileUser.getUsername());
        userDto.setCreationDate(userRepository.getCreationDateById(userId));
        userDto.setId(userId);
        model.addAttribute("profileUser", userDto);
        model.addAttribute("postsQuantity", postRepository.countByUserId(userId));
        model.addAttribute("followers", userRepository.countByFollowedId(userId));
        model.addAttribute("followeds", userRepository.countByFollowerId(userId));
        return "profile";
    }

    @GetMapping("/edit/{userId}")
    public String editProfile(@PathVariable Integer userId, HttpSession session){
        System.out.println("------------------ [EDIT PROFILE] Of " + userId + "------------------");
        return "edit";
    }

    @PostMapping("/follow-user/{userId}")
    public ResponseEntity<Boolean> followUser(@PathVariable("userId") Integer followedId, HttpSession session, HttpServletResponse response){
        System.out.println("------------------ [FOLLOW-USER] ------------------");
        
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            customUserDetailsService.followUser(sessionUser.getId(), followedId);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

    @PostMapping("/unfollow-user/{userId}")
    public ResponseEntity<Boolean> unfollowUser(@PathVariable("userId") Integer unfollowedId, HttpSession session, HttpServletResponse response){
        System.out.println("------------------ [FOLLOW-USER] ------------------");
        
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            customUserDetailsService.unfollowUser(sessionUser.getId(), unfollowedId);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
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
