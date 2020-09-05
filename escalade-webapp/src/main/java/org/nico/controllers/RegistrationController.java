package org.nico.controllers;

import org.nico.business.contract.manager.UserManager;
import org.nico.business.contract.manager.PasswordManager;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.inject.Inject;


@Controller
public class RegistrationController {

    @Inject
    private UserManager userManager;

    @Inject
    private PasswordManager passwordManager;


    @GetMapping(value = "/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/registerProcess")
    public String userRegistration(@ModelAttribute("user") User newUser, BindingResult bindingResult, Model model) {

            if (bindingResult.hasErrors()) {

                return "register";

            }else {

            String hashPassword = passwordManager.hashPassword(newUser.getPassword());
            newUser.setPassword(hashPassword);
            newUser.setRole(Role.USER.getParam());
            userManager.createUser(newUser);

            model.addAttribute("message", "Inscription réussie.");
            model.addAttribute("user", newUser);

            return "home";
        }
    }
}
