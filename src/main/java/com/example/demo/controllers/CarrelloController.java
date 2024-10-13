package com.example.demo.controllers;

import com.example.demo.services.LibroService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.demo.GlobalApp.NOME_SITO_WEB_KEY;
import static com.example.demo.GlobalApp.NOME_SITO_WEB_VALUE;

@Slf4j
@Controller
@Transactional
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    LibroService libroService;

    @GetMapping(value = {"", "/", "/index" })
    public String index(ModelMap model, HttpSession session) {
        model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
        model.addAttribute("libri", libroService.getAllBooks());
        return "carrello/index";
    }

    @RequestMapping("/aggiungi/{idlibro}")
    public String aggiungi (@PathVariable("idlibro") int id, Model model){
        model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
        model.addAttribute("libro", this.libroService.getLibroByidlibro(id));
        return "carrello/aggiungi";
    }

}
