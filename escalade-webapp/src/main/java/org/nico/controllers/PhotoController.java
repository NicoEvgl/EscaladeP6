package org.nico.controllers;

import org.nico.business.contract.manager.PhotoManager;
import org.nico.model.beans.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;


@Controller
public class PhotoController {

    @Inject
    private PhotoManager photoManager;

    @GetMapping("/climbingSite/{climbingSiteId}/photoForm")
    public String displayClimbingSitePhotoForm(@PathVariable Integer climbingSiteId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }
        model.addAttribute("climbingSiteId", climbingSiteId);
        model.addAttribute("photo", new Photo());

        return "photoForm";
    }
}
