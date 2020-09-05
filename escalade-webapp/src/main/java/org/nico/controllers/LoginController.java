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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Inject
    private UserManager userManager;

    @Inject
    private PasswordManager passwordManager;

    private void addUserInSession(User user, HttpSession httpSession) {
        httpSession.setAttribute("userInSessionUsername", user.getUsername());
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
            model.addAttribute("message", "Mot de passe incorrect");
            return "login";
        } else {
            User loggedInUser = registeredUser;
            addUserInSession(loggedInUser, httpSession);
            model.addAttribute("message", "<div style='text-align:center;'/>"
                    + "<h3>Connexion réussie</h3>");
            model.addAttribute("login", "newLogin");
            return "home";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse httpServletResponse, HttpSession httpSession, WebRequest webRequest, SessionStatus sessionStatus, Model model){
       sessionStatus.setComplete();
       webRequest.removeAttribute("userInSessionUsername", WebRequest.SCOPE_SESSION);
       webRequest.removeAttribute("userInSessionRole", WebRequest.SCOPE_SESSION);
       httpServletResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
       httpServletResponse.setHeader("Pragma","no-cache");
       httpServletResponse.setHeader("Expires","0");
       httpSession.invalidate();

        return "redirect:/home";
    }

}

