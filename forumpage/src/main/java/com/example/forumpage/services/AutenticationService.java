package com.example.forumpage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.forumpage.model.User;
import com.example.forumpage.repositories.UserRepository;

@Service
public class AutenticationService{

    @Autowired
    private UserRepository userRepository;

    public User authentication(String email, String password){
        User user = userRepository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (user != null) {
            if(passwordEncoder.matches(password, user.getPassword())){
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
