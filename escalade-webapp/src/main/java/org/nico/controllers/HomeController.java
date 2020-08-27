package org.nico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public ModelAndView displayHomePage() {
        String message = "<div style='text-align:center;'/>"
                + "<h3>Bienvenue chez les amis de l'escalade</h3>";
        return new ModelAndView("home", "message", message);
    }

}
