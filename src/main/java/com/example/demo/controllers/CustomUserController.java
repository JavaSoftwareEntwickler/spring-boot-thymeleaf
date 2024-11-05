package com.example.demo.controllers;

import com.example.demo.mail.MailSenderService;
import com.example.demo.models.CustomUser;
import com.example.demo.services.CustomUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Slf4j
@Controller
public class CustomUserController {

    @Autowired
    private CustomUserServiceImpl userService;
    @Autowired
    private MailSenderService mailSenderService;

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
        try {
            mailSenderService.sendMail();
        } catch (IOException e) {
            log.error("Invio mail errore {}",e);
        }
        return "redirect:login?success";
    }

}

