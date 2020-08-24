package org.nico.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public ModelAndView displayHomePage() {
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Bienvenue chez les amis de l'escalade</h3>**********</div><br><br>";
        return new ModelAndView("home", "message", message);
    }

}
