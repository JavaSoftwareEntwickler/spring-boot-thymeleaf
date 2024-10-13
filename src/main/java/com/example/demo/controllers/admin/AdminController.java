package com.example.demo.controllers.admin;

import com.example.demo.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Libro;
import com.example.demo.services.AutoreService;
import com.example.demo.services.GenereService;
import com.example.demo.services.LibroService;

import java.io.File;
import java.time.LocalDate;

import static com.example.demo.GlobalApp.*;

@Slf4j
@Controller
@Transactional
public class AdminController {
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private GenereService genereService;

	@Autowired
	FileService fileService;

	@GetMapping({ "/admin", "/admin/index" })
	public String allclienti(Model model) {
		model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
		model.addAttribute("libri", libroService.getAllBooks());
		return "admin/index";
	}

	@RequestMapping("/admin/insert")
	public String inserisciLibro(Libro libro, Model model) {
		LocalDate dataInserimento = LocalDate.now();
		model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
		model.addAttribute("dataInserimento", dataInserimento);
		model.addAttribute("nomeSito", "The blog name!");
		model.addAttribute("autori", this.autoreService.getAllAutori());
		model.addAttribute("generi", this.genereService.getAllGeneri());
		return "admin/insert";
	}

	@RequestMapping(value = "/admin/insertBook", method = RequestMethod.POST)
	public String insertBook(
			@Valid @ModelAttribute("libro") Libro libro,
			BindingResult bindingResult,
			Model model,
			@RequestParam(value = "file", required = false) @Valid MultipartFile file,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("libro", libro);
			model.addAttribute("autori", this.autoreService.getAllAutori());
			model.addAttribute("generi", this.genereService.getAllGeneri());
			return "/admin/insert";
		}
        fileService.save(file);

        libro.setCopertina(file.getOriginalFilename());
		libroService.insertLibro(libro);
		return "redirect:/admin/index";
	}

	@RequestMapping("/admin/edit/{idlibro}")
	public String editLibro(
			@PathVariable("idlibro") int idlibro,
			Model model) {
		model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
		model.addAttribute("libro", this.libroService.getLibroByidlibro(idlibro));
		Libro b = this.libroService.getLibroByidlibro(idlibro);
		model.addAttribute("autorichecked", b.getAutori());
		model.addAttribute("autori", this.autoreService.getAllAutori());
		model.addAttribute("generi", this.genereService.getAllGeneri());
		return "admin/edit";
	}

	// vincolo tra modello e visualizzazione (htlm abbiamo avete un th:objeckt)
	@RequestMapping(value = "/admin/updateBook", method = RequestMethod.POST)
	public ModelAndView updateBook(
			@ModelAttribute(value = "libro") Libro book,
			@RequestParam(value = "file", required = false) @Valid MultipartFile file,
			Model model) {
		String nomeCopertina = file.getOriginalFilename();
		if(!file.isEmpty()){
            fileService.save(file);
            //Non cancello l'immagine di default
			if(!book.getCopertina().equals("default.png")) {
				File oldFileToDelete = new File(fileService.getDirectory() + book.getCopertina());
				oldFileToDelete.delete();
			}
			book.setCopertina(nomeCopertina);
		}
		libroService.updateLibro(book);
		model.addAttribute("libro",book);
		return new ModelAndView("redirect:/admin/index");
	}

	@RequestMapping("admin/delete/{idlibro}")
	public String deleteBook(@PathVariable("idlibro") int idlibro, Model model) {
		model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
		model.addAttribute("libro", this.libroService.getLibroByidlibro(idlibro));
		model.addAttribute("autori", this.autoreService.getAllAutori());
		model.addAttribute("generi", this.genereService.getAllGeneri());
		return "admin/delete";
	}

	@RequestMapping(value = "/admin/deleteLibro", method = RequestMethod.POST)
	public ModelAndView deleteLibro(@ModelAttribute(value = "libro") Libro book) {
		book.setIdgenere(1);
		libroService.deleteLibro(book);
		return new ModelAndView("redirect:/admin/index");
	}

}
