package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.PhotoManager;
import org.nico.model.beans.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.validation.Valid;


@Controller
public class PhotoController {

    @Inject
    private PhotoManager photoManager;
    @Inject
    private ClimbingSiteManager climbingSiteManager;


    @GetMapping("/climbingSite/{climbingSiteId}/photoForm")
    public String displayClimbingSitePhotoForm(@PathVariable Integer climbingSiteId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }
        model.addAttribute("climbingSiteId", climbingSiteId);
        model.addAttribute("photo", new Photo());

        return "photoForm";
    }

    @PostMapping("/climbingSite/{climbingSiteId}/addPhotoProcess")
    public String addClimbingSitePhoto(@PathVariable Integer climbingSiteId, @Valid Photo photo, BindingResult bindingResult, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "Erreur... La photo n'a pu être ajouter");
            model.addAttribute("climbingSiteId", climbingSiteId);
            model.addAttribute("photo", photo);
            return "photoForm";
        } else {
            photo.setClimbingSite(climbingSiteManager.findClimbingSite(climbingSiteId));
            photoManager.createPhoto(photo);
            model.addAttribute("climbingSiteId", climbingSiteId);
            model.addAttribute("userInSessionId", userInSessionId);
            return "redirect:/climbingSite/{climbingSiteId}";
        }
    }
}
