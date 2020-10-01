package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.PhotoManager;
import org.nico.model.beans.ClimbingSite;
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
import java.util.List;


@Controller
public class PhotoController {

    @Inject
    private PhotoManager photoManager;
    @Inject
    private ClimbingSiteManager climbingSiteManager;


    /**
     * Display form to add a photo.
     * @param climbingSiteId
     * @param model
     * @param userInSessionId
     * @return photoForm
     */
    @GetMapping("/climbingSite/{climbingSiteId}/photoForm")
    public String displayClimbingSitePhotoForm(@PathVariable Integer climbingSiteId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }
        model.addAttribute("climbingSiteId", climbingSiteId);
        model.addAttribute("photo", new Photo());

        return "photoForm";
    }

    /**
     * Process to add a photo
     * @param climbingSiteId
     * @param photo
     * @param bindingResult
     * @param model
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
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

    /**
     * Display climbing site's photos.
     * @param climbingSiteId
     * @param model
     * @param userInSessionId
     * @return photoList
     */
    @GetMapping("/climbingSite/{climbingSiteId}/photoList")
    public String displayClimbingSitePhotoList(@PathVariable Integer climbingSiteId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }
        List<Photo> photoList = photoManager.findPhotoByClimbingSite(climbingSiteId);
        ClimbingSite climbingSite = climbingSiteManager.findClimbingSite(climbingSiteId);

        model.addAttribute("climbingSite", climbingSite);
        model.addAttribute("climbingSiteId", climbingSiteId);
        model.addAttribute("photoList", photoList);

        return "photoList";
    }

    /**
     * Display form to edit a photo.
     * @param id
     * @param model
     * @param userInSessionId
     * @return photoEditForm
     */
    @GetMapping("/editPhoto/{id}")
    public String displayPhotoEditForm(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }

        Photo editedPhoto = photoManager.findPhoto(id);
        model.addAttribute("editedPhoto", editedPhoto);
        model.addAttribute("userInSessionId", userInSessionId);
        return "photoEditForm";
    }

    /**
     * Process to edit a photo.
     * @param id
     * @param photo
     * @param model
     * @param bindingResult
     * @param userInSessionId
     * @return "/climbingSite/{id}/photoList"
     */
    @PostMapping("/editPhoto/editPhotoProcess/{id}")
    public String editPhoto(@PathVariable Integer id, @Valid Photo photo, Model model, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "Erreur... La photo n'a pu être modifiées");
            model.addAttribute("editedPhoto", photoManager.findPhoto(id));
            model.addAttribute("userInSessionId", userInSessionId);
            return "photoEditForm";
        } else {
            photoManager.updatePhoto(photo);
            model.addAttribute("climbingSiteId", photo.getClimbingSite().getId());
            model.addAttribute("userInSessionId", userInSessionId);
            return "redirect:/climbingSite/{climbingSiteId}/photoList";
        }
    }

    /**
     * Method to delete a photo.
     * @param id
     * @param model
     * @param userInSessionId
     * @return "/ClimbingSite/{id}/photoList"
     */
    @GetMapping("/deletePhoto/{id}")
    public String deletePhoto(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        model.addAttribute("climbingSiteId", photoManager.findPhoto(id).getClimbingSite().getId());
        photoManager.deletePhoto(id);
        return "redirect:/climbingSite/{climbingSiteId}/photoList";
    }
}
