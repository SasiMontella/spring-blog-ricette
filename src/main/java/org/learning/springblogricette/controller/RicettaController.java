package org.learning.springblogricette.controller;

import jakarta.validation.Valid;
import org.learning.springblogricette.model.Categoria;
import org.learning.springblogricette.model.Ricetta;
import org.learning.springblogricette.repository.CategoriaRepository;
import org.learning.springblogricette.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ricette")
public class RicettaController {
    @Autowired
    RicettaRepository ricettarepository;
    @Autowired
    CategoriaRepository categoriarepository;

    //Metodo che mostra la lista delle ricette
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword, Model model) {
        List<Ricetta> ricetteList;
        if (searchKeyword != null) {
            ricetteList = ricettarepository.findByTitleContaining(searchKeyword);
        } else {
            ricetteList = ricettarepository.findAll();
        }
        model.addAttribute("recipes", ricetteList);
        model.addAttribute("preloadSerch", searchKeyword);
        return "ricette/index";
    }

    //Metodo che mostra i dettagli di una ricetta
    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettarepository.findById(id);
        if (result.isPresent()) {
            Ricetta formricetta = result.get();
            model.addAttribute("ricetta", formricetta);
            return "ricette/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La ricetta con id: " + id + " non esiste");
        }
    }

    // metodo create per una ricetta
    @GetMapping("/create")
    public String create(Model model) {
        List<Categoria> categoriaList = categoriarepository.findAll();
        model.addAttribute("categoryList", categoriaList);
        model.addAttribute("ricetta", new Ricetta());
        return "ricette/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ricetta") Ricetta formricetta, Model model, BindingResult bindingresult) {
        if (bindingresult.hasErrors()) {
            model.addAttribute("categoryList", categoriarepository.findAll());
            model.addAttribute("ricettaList", ricettarepository.findAll());
            return "ricette/create";
        }
        ricettarepository.save(formricetta);
        return "redirect:/ricette";
    }

    //metodo delete di una ricetta
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ricettarepository.deleteById(id);
        return "redirect:/ricette";
    }

    //metodo update di una ricetta
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettarepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("recipe", result.get());
            model.addAttribute("categoryList", categoriarepository.findAll());
            return "ricette/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La ricetta con id: " + id + " non esiste");
        }
    }

    @PostMapping("/edit/{id}")
    public String store(@PathVariable Integer id, Model model, @Valid @ModelAttribute("formRicetta") Ricetta formRicetta, BindingResult bindingresult) {
        if (bindingresult.hasErrors()) {
            model.addAttribute("categoryList", categoriarepository.findAll());
            return "ricette/edit";
        }
        ricettarepository.save(formRicetta);
        return "redirect:/ricette";
    }

}
