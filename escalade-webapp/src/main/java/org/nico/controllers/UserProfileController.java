package org.nico.controllers;

import org.nico.business.contract.manager.UserManager;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import java.util.List;

@Controller
public class UserProfileController {

    @Inject
    private UserManager userManager;

    @GetMapping("/userProfile/{userProfileId}")
    public String displayUserProfile(@PathVariable @SessionAttribute("userProfileId")Integer userProfileId, Model model){


        User userProfile = userManager.findUser(userProfileId);

        model.addAttribute("userProfile", userProfile);
        model.addAttribute("userProfileId", userProfileId);

        return "userProfile";
    }
}
