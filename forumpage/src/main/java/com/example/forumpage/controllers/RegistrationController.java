package com.example.forumpage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forumpage.model.User;
import com.example.forumpage.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(User user) {
        return "register";
    }

    @PostMapping("/process_register")
    @Transactional
    public String processRegister(@Valid @ModelAttribute User user, BindingResult result, @RequestParam("confirmPassword") String confirmPassword, HttpSession session) {

        if (result.hasErrors() || !user.getPassword().equals(confirmPassword)) {
            // result.reject("confirmPasswordError", "Las contraseñas no coinciden");
            
            ObjectError error = new ObjectError("confirmPasswordError", "Las contrasenas no coinciden");
            result.addError(error); 
            return "register";
            }
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/";
    }

}
