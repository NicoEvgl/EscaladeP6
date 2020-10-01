package org.nico.controllers;

import org.nico.business.contract.manager.*;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClimbingSiteController {

    @Inject
    private ClimbingSiteManager climbingSiteManager;
    @Inject
    private EnumManager enumManager;
    @Inject
    private PhotoManager photoManager;
    @Inject
    private UserManager userManager;
    @Inject
    private SectorManager sectorManager;
    @Inject
    private RouteManager routeManager;
    @Inject
    private CommentManager commentManager;


    /**
     * Display climbing site list page.
     * @param model
     * @param userInSessionId
     * @return climbingSiteList
     */
    @GetMapping("/climbingSiteList")
    public String displayClimbingSiteList(Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId) {
        List<ClimbingSite> climbingSiteList = climbingSiteManager.findClimbingSiteList();
        List<String> regionList = enumManager.getEnumRegionStringValues();
        List<String> quotationList = enumManager.getEnumQuotationStringValues();
        List<String> nameList = new ArrayList<>();
        List<Integer> nbRoutesList = new ArrayList<>();

        for (ClimbingSite climbingSite : climbingSiteList){
            climbingSite.setPhotoList(photoManager.findPhotoByClimbingSite(climbingSite.getId()));
        }

        for (ClimbingSite climbingSite : climbingSiteList){
            nameList.add(climbingSite.getName());
        }
        for (int i = 0; i < 101; i++){
            nbRoutesList.add(i);
        }
        model.addAttribute("regionList", regionList);
        model.addAttribute("quotationList", quotationList);
        model.addAttribute("nameList", nameList);
        model.addAttribute("nbRoutesList", nbRoutesList);
        model.addAttribute("searchFilter", new SearchFilter());
        model.addAttribute("climbingSiteList", climbingSiteList);

        return "climbingSiteList";
    }

    /**
     * Display climbing site page
     * @param id
     * @param userInSessionId
     * @param model
     * @return climbingSite
     */
    @GetMapping("/climbingSite/{id}")
    public String displayClimbingSite(@PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, Model model){
        ClimbingSite climbingSite = climbingSiteManager.findClimbingSite(id);
        List<Photo> photoList = photoManager.findPhotoByClimbingSite(id);
        List<Sector> sectorList = sectorManager.findSectorByClimbingSite(id);
        List<Comment> commentList = commentManager.findCommentByClimbingSite(id);

        climbingSite.setPhotoList(photoList);
        climbingSite.setSectorList(sectorList);
        climbingSite.setCommentList(commentList);
        for (Sector sector : sectorList){
            List<Route> routeList = new ArrayList<>();
            for (Route route : routeManager.findRouteBySector(sector.getId())){
                routeList.add(route);
            }
            sector.setRouteList(routeList);
        }
        model.addAttribute("climbingSite", climbingSite);
        model.addAttribute("userInSessionId", userInSessionId);


        return "climbingSite";
    }

    /**
     * Display form page to add a new climbing site.
     * @param model
     * @param userInSessionId
     * @return climbingSiteForm
     */
    @GetMapping("/addClimbingSite")
    public String displayClimbingSiteForm(Model model, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        List<String> regionList = enumManager.getEnumRegionStringValues();
        List<String> quotationList = enumManager.getEnumQuotationStringValues();

        model.addAttribute("regionList", regionList);
        model.addAttribute("quotationList", quotationList);
        model.addAttribute("climbingSite", new ClimbingSite());
        return "climbingSiteForm";
    }

    /**
     * Method Post to add a new climbing site.
     * @param newClimbingSite
     * @param bindingResult
     * @param model
     * @param userInSessionId
     * @return method getMapping("/climbingSiteList")
     */
    @PostMapping("/addClimbingSiteProcess")
    public String addClimbingSite(@Valid @ModelAttribute("climbingSite") ClimbingSite newClimbingSite, BindingResult bindingResult, Model model, @SessionAttribute(value = "userInSessionId", required = true) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        ClimbingSite registeredClimbingSite = climbingSiteManager.findClimbingSiteByAttribute("name", newClimbingSite.getName());
        if (registeredClimbingSite != null){
            model.addAttribute("errorMessage", "Désolé ce site existe déjà");
            return "redirect:/addClimbingSite";
        } else {
            if (bindingResult.hasErrors()){
                model.addAttribute("errorMessage", "Une erreur est survenue. Vérifiez les champs.");
                return "redirect:/addClimbingSite";
            } else {
                newClimbingSite.setUser(userManager.findUser(userInSessionId));
                climbingSiteManager.createClimbingSite(newClimbingSite);
                return "redirect:/climbingSiteList";
            }
        }
    }

    /**
     * Display form page to edit a climbing site.
     * @param model
     * @param id
     * @param userInSessionId
     * @return climbingSiteEditForm
     */
    @GetMapping("/editClimbingSite/{id}")
    public String displayClimbingSiteEditForm(Model model, @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        ClimbingSite editedClimbingSite = climbingSiteManager.findClimbingSite(id);
        List<String> regionList = enumManager.getEnumRegionStringValues();
        List<String> quotationList = enumManager.getEnumQuotationStringValues();

        model.addAttribute("regionList", regionList);
        model.addAttribute("quotationList", quotationList);
        model.addAttribute("editedClimbingSite", editedClimbingSite);

        return "climbingSiteEditForm";
    }

    /**
     * Method Post to edit a climbing site
     * @param climbingSite
     * @param bindingResult
     * @param model
     * @param id
     * @param userInSessionId
     * @return method getMapping("/climbingSite/{id}")
     */
    @PostMapping("/editClimbingSite/editClimbingSiteProcess/{id}")
    public String editClimbingSite(@Valid ClimbingSite climbingSite, BindingResult bindingResult, Model model, @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        ClimbingSite registeredClimbingSite = climbingSiteManager.findClimbingSite(id);
        climbingSite.setUser(registeredClimbingSite.getUser());

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            String str = "Une erreur est survenue. Vérifiez les champs.";

            model.addAttribute("editedClimbingSite", climbingSite);
            model.addAttribute("errorMessage", str);

            return "climbingSiteEditForm";
        } else {
            climbingSiteManager.updateClimbingSite(climbingSite);

            model.addAttribute("message", "Modificaions enregistrées");
            model.addAttribute("id", id);

            return "redirect:/climbingSite/{id}";
        }
    }

    /**
     * Method to delete a climbing site.
     * @param id
     * @param model
     * @param userInSessionId
     * @return
     */
    @GetMapping("/deleteClimbingSite/{id}")
    public String deleteClimbingSite(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        List<Photo> photoList = photoManager.findPhotoByClimbingSite(id);
        for (Photo photo : photoList){
            photoManager.deletePhoto(photo.getId());
        }
        climbingSiteManager.deleteClimbingSite(id);
        return "redirect:/climbingSiteList";
    }

    /**
     * Display page to tag a climbing site as official "Les amis de l'escalade".
     * @param model
     * @param id
     * @param userInSessionId
     * @return climbingSiteTag
     */
    @GetMapping("/climbingSiteTag/{id}")
    public String displayClimbingSiteTag(Model model, @PathVariable Integer id,
                                          @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        ClimbingSite tagedClimbingSite = climbingSiteManager.findClimbingSite(id);
        model.addAttribute("tagedClimbingSite", tagedClimbingSite);
        return "climbingSiteTag";
    }

    /**
     * Method Post to tag a climbing site as official "Les amis de l'escalade".
     * @param climbingSite
     * @param bindingResult
     * @param model
     * @param id
     * @param userInSessionId
     * @return method getMapping("/climbingSite/{id}")
     */
    @PostMapping("/climbingSiteTag/climbingSiteTagProcess/{id}")
    public String climbingSiteTag(@Valid ClimbingSite climbingSite, BindingResult bindingResult, Model model,
                                        @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId == null) {
            return "redirect:/login";
        }
        ClimbingSite registeredClimbingSite = climbingSiteManager.findClimbingSite(id);
        climbingSite.setUser(registeredClimbingSite.getUser());

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            String str = "Une erreur est survenue. Vérifiez les champs.";
            model.addAttribute("registeredClimbingSite", climbingSite);
            model.addAttribute("errorMessage", str);
            return "climbingSiteTag";
        } else {
            if (climbingSite.isCertified() == true){
                climbingSiteManager.addTag(id);
            } else {
                climbingSiteManager.deleteTag(id);
            }
            model.addAttribute("id", id);
            return "redirect:/climbingSite/{id}";
        }
    }
}
