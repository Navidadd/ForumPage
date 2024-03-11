package com.example.forumpage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forumpage.model.User;
import com.example.forumpage.services.AutenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AutenticationService autenticationService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/process_login")
    public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        
        User sessionUser = autenticationService.authentication(email, password);
        
        if(sessionUser != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", sessionUser);
            return "redirect:/";
        }else{
            System.out.println(email);
            System.out.println(password);
            model.addAttribute("errorMessage", "Credenciales incorrectas");
            return "login";
        }
    }
}
