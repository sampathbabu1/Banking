package com.example.Banking.controller;

import com.example.Banking.entity.User;

import com.example.Banking.repository.UserRepository;
import com.example.Banking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/signup")
    public String getSignup(Model theModel){
        User user=new User();
        theModel.addAttribute("user",user);
        return "signup";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "redirect:/login";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute("user") User user){
       userService.saveUser(user);
        return "redirect:/login";
    }
}
