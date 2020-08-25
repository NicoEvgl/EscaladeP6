package org.nico.controllers;

import org.nico.model.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
