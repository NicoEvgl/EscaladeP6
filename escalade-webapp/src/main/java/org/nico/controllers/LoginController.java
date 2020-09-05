package org.nico.controllers;

import org.nico.business.contract.manager.UserManager;
import org.nico.business.contract.manager.PasswordManager;
import org.nico.model.beans.Login;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Inject
    private UserManager userManager;

    @Inject
    private PasswordManager passwordManager;

    private void addUserInSession(User user, HttpSession httpSession) {
        httpSession.setAttribute("userInSessionUsername", user.getUsername());
        httpSession.setAttribute("userInSessionPassword", user.getPassword());
        httpSession.setAttribute("userInSessionRole", user.getRole());
    }


    @GetMapping(value = "/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping(value = "/loginProcess")
    public String doLogin(@ModelAttribute("login") Login newLogin, HttpSession httpSession, Model model) {

        User registeredUser = userManager.findUserByAttribute("username", newLogin.getUsername());

        boolean checkPassword = true;

        checkPassword = passwordManager.matches(newLogin.getPassword(), registeredUser.getPassword());

        if (checkPassword == false ) {
            httpSession.invalidate();
            String errorMessage = "<div style='text-align:center;'/>"
                    + "<h3>Mot de passe incorrect</h3>";
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        } else {
            User loggedInUser = registeredUser;
            addUserInSession(loggedInUser, httpSession);
            String message = "<div style='text-align:center;'/>"
                    + "<h3>Connexion réussie</h3>";
            model.addAttribute("login", "newLogin");
            model.addAttribute("message", message);

            return "welcome";
        }
    }

}

