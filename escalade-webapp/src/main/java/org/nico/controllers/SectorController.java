package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.RouteManager;
import org.nico.business.contract.manager.SectorManager;
import org.nico.model.beans.Route;
import org.nico.model.beans.Sector;
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
public class SectorController {

    @Inject
    private ClimbingSiteManager climbingSiteManager;
    @Inject
    private SectorManager sectorManager;
    @Inject
    private RouteManager routeManager;


    /**
     * Display form to add a sector.
     * @param climbingSiteId
     * @param model
     * @param userInSessionId
     * @return sectorForm
     */
    @GetMapping("/addSector/{climbingSiteId}")
    public String displaySectorForm(@PathVariable Integer climbingSiteId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            Sector sector = new Sector();
            sector.setClimbingSite(climbingSiteManager.findClimbingSite(climbingSiteId));

            model.addAttribute("sector", sector);
            model.addAttribute("climbingSiteId", climbingSiteId);
            return "sectorForm";

        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to add a sector.
     * @param climbingSiteId
     * @param model
     * @param sector
     * @param bindingResult
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
    @PostMapping("/addSector/addSectorProcess/{climbingSiteId}")
    public String addSector(@PathVariable Integer climbingSiteId, Model model, @Valid Sector sector, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){

        if (userInSessionId != null){
            if (bindingResult.hasErrors()){
                model.addAttribute("errorMessage", "Une erreur est survenue. Vérifiez les champs.");

                return "sectorForm";
            } else {
                sectorManager.createSector(sector);

                return "redirect:/climbingSite/{climbingSiteId}";
            }
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Display form to edit a sector.
     * @param model
     * @param id
     * @param userInSessionId
     * @return sectorEditForm
     */
    @GetMapping("/editSector/{id}")
    public String displaySectorEditForm(Model model, @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId != null){
            Sector editedSector = sectorManager.findSector(id);

            model.addAttribute("userInSessionId", userInSessionId);
            model.addAttribute("editedSector", editedSector);
            return "sectorEditForm";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to edit a sector.
     * @param sector
     * @param id
     * @param model
     * @param bindingResult
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
    @PostMapping("/editSector/editSectorProcess/{id}")
    public String editSector(@Valid Sector sector, @PathVariable Integer id, Model model, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId != null){
            if (bindingResult.hasErrors()){
                model.addAttribute("userInSessionId", userInSessionId);
                model.addAttribute("editedSector", sectorManager.findSector(id));
                return  "sectorEditForm";
            } else {
                sectorManager.updateSector(sector);
                model.addAttribute("climbingSiteId", sector.getClimbingSite().getId());
                model.addAttribute("userInSessionId", userInSessionId);
                return "redirect:/climbingSite/{climbingSiteId}";
            }
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Method to delete a sector.
     * @param id
     * @param model
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
    @GetMapping("/deleteSector/{id}")
    public String deleteSector(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            model.addAttribute("climbingSiteId", sectorManager.findSector(id).getClimbingSite().getId());
            List<Route> routeList = routeManager.findRouteBySector(id);
            for (Route route : routeList){
                routeManager.deleteRoute(route.getId());
            }
            sectorManager.deleteSector(id);
            return "redirect:/climbingSite/{climbingSiteId}";
        } else {
            return "redirect:/login";
        }
    }
}
