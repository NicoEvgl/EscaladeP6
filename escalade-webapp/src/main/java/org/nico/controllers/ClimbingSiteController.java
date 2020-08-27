package org.nico.controllers;

import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClimbingSiteController {

    @GetMapping(value = "/climbingSite")
    public String displayClimbingSitePage(Model model) {
        model.addAttribute("climbingSite", new ClimbingSite());
        return "climbingSite";
    }
}
