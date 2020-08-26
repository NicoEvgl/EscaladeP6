package org.nico.controllers;

import org.nico.model.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }
}
