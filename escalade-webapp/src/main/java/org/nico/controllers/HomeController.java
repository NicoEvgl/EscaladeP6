package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.PhotoManager;
import org.nico.model.beans.ClimbingSite;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public String displayHomePage(Model model , @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, HttpSession httpSession){
        if(userInSessionId == null){
            httpSession.invalidate();
        }
        model.addAttribute("userInSessionId", userInSessionId);
        return "home";
    }
}
