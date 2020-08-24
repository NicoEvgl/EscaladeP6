package org.nico.webapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView displayHomePage() {
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Bienvenue sur la plateforme des amis de l'escalade</h3>This message is coming from HomeController.java **********</div><br><br>";
        return new ModelAndView("home", "message", message);
    }

}
