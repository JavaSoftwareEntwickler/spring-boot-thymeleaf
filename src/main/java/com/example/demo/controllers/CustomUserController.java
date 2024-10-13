package com.example.demo.controllers;

import com.example.demo.models.CustomUser;
import com.example.demo.services.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomUserController {

    @Autowired
    private CustomUserServiceImpl userService;

    @GetMapping("/login")
    String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user" , CustomUser.builder().build());
        return "register";
    }

    @PostMapping("/register")
    String registration(@ModelAttribute CustomUser user) {
        userService.registerUser(user);
        return "redirect:login?success";
    }

}
