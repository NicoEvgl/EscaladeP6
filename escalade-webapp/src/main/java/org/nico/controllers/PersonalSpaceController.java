package org.nico.controllers;


import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.PasswordManager;
import org.nico.business.contract.manager.PhotoManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@SessionAttributes("userInSessionId")
public class PersonalSpaceController {

    @Inject
    private UserManager userManager;
    @Inject
    private ClimbingSiteManager climbingSiteManager;
    @Inject
    private PhotoManager photoManager;

    @GetMapping("/personalSpace/{memberInSessionId}")
    public String displayPersonalSpace(@PathVariable@SessionAttribute("userInSessionId")Integer userInSessionId, Model model){
        if (userInSessionId == null){
            return "redirect:/showHome";
        }

        User registeredUser = userManager.findUser(userInSessionId);
        if (registeredUser.getRole() == Role.ADMIN.getParam()){
            List<User> userList = userManager.findUserList();
            model.addAttribute("userList", userList);
        }
        //show all the climbing area's owner
        List<ClimbingSite> climbingSiteList = climbingSiteManager.findClimbingSiteByUserId(userInSessionId);
        for (ClimbingSite climbingSite : climbingSiteList){
            climbingSite.setPhotoList(photoManager.findPhotoByClimbingSiteId(climbingSite.getId()));
        }

        model.addAttribute("userInSessionId", userInSessionId);
        model.addAttribute("climbingSiteList", climbingSiteList);

        return "personalSpace";
    }

}
