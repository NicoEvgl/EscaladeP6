package org.nico.controllers;

import org.nico.model.beans.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping(value = "/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }
}
