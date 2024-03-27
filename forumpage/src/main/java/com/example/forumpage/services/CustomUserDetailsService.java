package com.example.forumpage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.forumpage.model.CustomUserDetails;
import com.example.forumpage.model.User;
import com.example.forumpage.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public void followUser(Integer followerId, Integer followedId){
        userRepository.addFollower(followerId, followedId);
    }

    public void unfollowUser(Integer followerId, Integer followedId){
        userRepository.deleteFollower(followerId, followedId);
    }

}
