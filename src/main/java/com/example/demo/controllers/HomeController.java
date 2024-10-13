package com.example.demo.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(value = { "/", "/index" })
	public String index(ModelMap model, HttpSession session) {
		model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
		model.addAttribute("libri", libroService.getAllBooks());
		return "index";
	}

}
