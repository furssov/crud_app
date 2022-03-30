package furssov.spring.controller;


import furssov.spring.dao.PersonDAO;
import furssov.spring.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@org.springframework.stereotype.Controller
@RequestMapping("/people")
public class Controller {
    private final PersonDAO personDAO;
       @Autowired
    public Controller(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String peopleList(Model model){
          model.addAttribute("people",personDAO.getPeople());
        return "people/list";
    }

    @GetMapping("/{id}")
    public String person(@PathVariable("id") int id, Model model){
           model.addAttribute("person",personDAO.getById(id));
           return "people/person";
    }



    @GetMapping("/create")
    public String newPerson(@ModelAttribute("person") Person person){
           return "people/create";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
           if(bindingResult.hasErrors())
               return "people/create";

              personDAO.save(person);
           return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")int id){
           model.addAttribute("person", personDAO.getById(id));
           return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
           if (bindingResult.hasErrors())
               return "people/edit";
           personDAO.update(id, person);
           return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
           personDAO.delete(id);
           return "redirect:/people";
    }

}
