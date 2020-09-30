package org.nico.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nico.business.contract.manager.UserManager;
import org.nico.business.contract.manager.PasswordManager;
import org.nico.model.beans.Login;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.nico.model.exception.UserBlockedException;
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
        httpSession.setAttribute("userInSessionId", user.getId());
        httpSession.setAttribute("userInSessionUsername", user.getUsername());
        httpSession.setAttribute("userInSessionEmail", user.getEmail());
        httpSession.setAttribute("userInSessionRole", user.getRole());
    }

    private  static final Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping(value = "/loginProcess")
    public String doLogin(@ModelAttribute("login") Login login, HttpSession httpSession, Model model) throws UserBlockedException {

        logger.debug(login.toString());
        if (login != null) {
            User registeredUser = userManager.findUserByAttribute("username", login.getUsername());
            logger.debug(registeredUser.toString());
            boolean checkPassword = false;

            if (registeredUser == null) {
                httpSession.invalidate();
                model.addAttribute("message", "Pseudo ou Mot de passe incorrect");
                return "login";
            }
            checkPassword = passwordManager.matches(login.getPassword(), registeredUser.getPassword());
            logger.debug(checkPassword);
            if (checkPassword == true) {
                User loggedInUser = registeredUser;
                if (loggedInUser.getRole().equals(Role.ADMIN.getParam()) || loggedInUser.getRole().equals(Role.MEMBER.getParam()) || loggedInUser.getRole().equals(Role.USER.getParam())) {
                    addUserInSession(loggedInUser, httpSession);
                    model.addAttribute("message", "<div style='text-align:center;'/>"
                            + "<h3>Connexion réussie</h3>");
                    return "redirect:/home";
                } else {
                    UserBlockedException userBlockedException = new UserBlockedException("Compte invalide ou supprimé");
                    model.addAttribute("message", userBlockedException);
                    return "login";
                }
            } else {
                httpSession.invalidate();
                model.addAttribute("message", "Pseudo ou Mot de passe incorrect");
                return "login";
            }
        }
        model.addAttribute("login", "login");
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse httpServletResponse, HttpSession httpSession, WebRequest webRequest, SessionStatus sessionStatus, Model model){
        sessionStatus.setComplete();

        webRequest.removeAttribute("userInSessionId", WebRequest.SCOPE_SESSION);
        webRequest.removeAttribute("userInSessionUsername", WebRequest.SCOPE_SESSION);
        webRequest.removeAttribute("userInSessionEmail", WebRequest.SCOPE_SESSION);
        webRequest.removeAttribute("userInSessionRole", WebRequest.SCOPE_SESSION);

        httpServletResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        httpServletResponse.setHeader("Pragma","no-cache");
        httpServletResponse.setHeader("Expires","0");

        httpSession.invalidate();

        return "redirect:/home";
    }

}

