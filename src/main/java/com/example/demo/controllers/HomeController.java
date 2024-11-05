package com.example.demo.controllers;

import com.example.demo.models.CustomUser;
import com.example.demo.services.CustomUserServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.LibroService;

import static com.example.demo.GlobalApp.NOME_SITO_WEB_KEY;
import static com.example.demo.GlobalApp.NOME_SITO_WEB_VALUE;

@Controller
public class HomeController {
	@Autowired
	private LibroService libroService;

	@Autowired
	CustomUserServiceImpl customUserService;

	@GetMapping(value = { "/", "/index" })
	public String index(ModelMap model, HttpSession session, @AuthenticationPrincipal UserDetails userDetails) {
		if(userDetails != null)
			userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().forEach(System.out::println);
		model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
		model.addAttribute("libri", libroService.getAllBooks());
		return "index";
	}

}
