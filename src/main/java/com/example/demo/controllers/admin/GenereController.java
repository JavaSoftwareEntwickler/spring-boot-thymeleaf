package com.example.demo.controllers.admin;

import com.example.demo.models.Genere;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Libro;
import com.example.demo.services.GenereService;
import java.time.LocalDate;

import static com.example.demo.GlobalApp.*;

@Slf4j
@Controller
@Transactional
@RequestMapping("/admin/genere")
public class GenereController {

    @Autowired
    private GenereService genereService;


    @GetMapping({ "","/", "/index" })
    public String allGeneri(Model model) {
        model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
        model.addAttribute("generi", genereService.getAllGeneri());
        return "admin/genere/index";
    }

    @RequestMapping("/insert")
    public String inserisciGenere(Genere genere, Model model) {
        LocalDate dataInserimento = LocalDate.now();
        model.addAttribute(NOME_SITO_WEB_KEY, NOME_SITO_WEB_VALUE);
        model.addAttribute("dataInserimento", dataInserimento);
        model.addAttribute("nomeSito", "The blog name!");
        return "admin/genere/insert";
    }

    @RequestMapping(value = "/insertGenere", method = RequestMethod.POST)
    public String insertGenere(
            @Valid @ModelAttribute("genere") Genere genere,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genere", genere);
            return "admin/genere/insert";
        }
        genereService.insertGenere(genere);
        return "redirect:/admin/genere/index";
    }
/*

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
*/

}
