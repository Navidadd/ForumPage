package com.example.forumpage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forumpage.model.User;
import com.example.forumpage.repositories.UserRepository;
import com.example.forumpage.services.AutenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AutenticationService autenticationService;

    @GetMapping("/login")
    public String loginPage(User user){
        return "login";
    }

    @PostMapping("/process_login")
    public String processLogin(User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        
        User existingUser = userRepository.findByEmail(email);
        
        if(existingUser != null){
            HttpSession session = request.getSession();
            User sessionUser = autenticationService.authentication(email, password);
            if(sessionUser != null){
                session.setAttribute("user", sessionUser);
                return "redirect:/";
            }else{
                model.addAttribute("passwordError", "Contraseña Incorrecta");
                return "login";
            }

        }else{
            model.addAttribute("emailError", "Email Incorrecto");
            return "login";
        }
    }
}
