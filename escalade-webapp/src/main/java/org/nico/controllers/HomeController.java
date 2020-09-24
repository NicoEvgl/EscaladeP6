package org.nico.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    static Logger logger = LogManager.getLogger(HomeController.class);


    @GetMapping({"/home", "/"})
    public String displayHomePage(Model model , @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, HttpSession httpSession){
        if(userInSessionId == null){
            logger.error("Utilisateur invalide");
            httpSession.invalidate();
        }
        model.addAttribute("userInSessionId", userInSessionId);
        return "home";
    }
}
