package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.files.FileService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Libro;
import com.example.demo.services.AutoreService;
import com.example.demo.services.GenereService;
import com.example.demo.services.LibroService;

import java.io.File;
import java.time.LocalDate;

@Controller
@Transactional
public class AdminController implements HandlerExceptionResolver {
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private GenereService genereService;

	@Autowired
	FileService fileService;
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error/error");
		modelAndView.getModel().put("message", "File troppo grande!");
		return modelAndView;
	}

	// Visualizza
	@GetMapping({ "/admin", "/admin/index" })
	public String allclienti(Model model) {
		model.addAttribute("NomeSito", "The blog name!");
		model.addAttribute("libri", libroService.getAllBooks());

		return "admin/index";
	}

	// Insert
	@RequestMapping("/admin/insert")
	public String inserisciLibro(Libro libro, Model model) {
		LocalDate dataInserimento = LocalDate.now();
		model.addAttribute("dataInserimento", dataInserimento);
		model.addAttribute("NomeSito", "The blog name!");
		model.addAttribute("autori", this.autoreService.getAllAutori());
		model.addAttribute("generi", this.genereService.getAllGeneri());
		return "admin/insert";
	}

	@RequestMapping(value = "/admin/insertBook", method = RequestMethod.POST)
	public String insertBook(
			@Valid @ModelAttribute("libro") Libro libro,
			BindingResult bindingResult,
			Model model,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			System.out.println("con erroriiiiiiiiiii "+libro.getDataInserimento());
			model.addAttribute("libro", libro);
			model.addAttribute("autori", this.autoreService.getAllAutori());
			model.addAttribute("generi", this.genereService.getAllGeneri());
			return "/admin/insert";
		}
		fileService.save(file);
		libro.setCopertina(file.getOriginalFilename());
		libroService.insertLibro(libro);
		model.addAttribute("libro", libro);
		return "redirect:/admin/index";
	}

	@RequestMapping("/admin/edit/{idlibro}")
	public String editLibro(
			@PathVariable("idlibro") int idlibro,
			Model model) {
		model.addAttribute("NomeSito", "The blog name!");
		model.addAttribute("libro", this.libroService.getLibroByidlibro(idlibro));
		Libro b = this.libroService.getLibroByidlibro(idlibro);
		model.addAttribute("autorichecked", b.getAutori());
		model.addAttribute("autori", this.autoreService.getAllAutori());
		model.addAttribute("generi", this.genereService.getAllGeneri());
		return "admin/edit";
	}

	// vincolo tra modello e visualizzazione (htlm deve avete un th:objeckt)
	@RequestMapping(value = "/admin/updateBook", method = RequestMethod.POST)
	public ModelAndView updateBook(
			@ModelAttribute(value = "libro") Libro book,
			@RequestParam(value = "file", required = false) MultipartFile file,
			Model model,
			MaxUploadSizeExceededException exc
			) {

		String nomeCopertina = file.getOriginalFilename();
		if(!file.isEmpty()){
			fileService.save(file);
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
		model.addAttribute("NomeSito", "The blog name!");
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
