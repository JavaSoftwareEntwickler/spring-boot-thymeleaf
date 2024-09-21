package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Libro;
import com.example.demo.services.LibroService;

@Controller
public class HomeController {
	@Autowired
	private LibroService libroService;

	@GetMapping(value = { "/", "/index" })
	public String index(ModelMap map, HttpSession session) {

		map.addAttribute("libri", libroService.getAllBooks());

		return "index";
	}

}
