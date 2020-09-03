package org.nico.controllers;

import org.nico.business.contract.manager.MemberManager;
import org.nico.business.contract.manager.PasswordManager;
import org.nico.model.beans.Member;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;

import javax.inject.Inject;
import javax.validation.Valid;


@Controller
public class RegistrationController {

    @Inject
    private MemberManager memberManager;

    @Inject
    private PasswordManager passwordManager;

    @GetMapping(value = "/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/registration")
    public String registerNewMember(@Valid Member newMember, BindingResult bindingResult, Model model) {
        Member existingMember = memberManager.findMemberByUsername("username", newMember.getUsername());

        if (existingMember != null){
            String str = "Ce pseudo existe déja";
            model.addAttribute("errorMessage", str);
            return "register";
        } else {
            if (bindingResult.hasErrors()) {
                return "register";
            }
            //encoder
            String hashPassword = passwordManager.hashPassword(newMember.getPassword());
            newMember.setPassword(hashPassword);
            newMember.setRole(Role.USER.getParam());
            memberManager.createMember(newMember);

            model.addAttribute("message", "Inscription réussie.");
            model.addAttribute("member", newMember);

            return "redirect:/Home";
        }
    }
}
