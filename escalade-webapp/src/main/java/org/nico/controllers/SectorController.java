package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.SectorManager;
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

@Controller
public class SectorController {

    @Inject
    private ClimbingSiteManager climbingSiteManager;
    @Inject
    private SectorManager sectorManager;


    @GetMapping("/sectorForm/{climbingSiteId}")
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

    @PostMapping("/sectorForm/addSectorProcess/{climbingSiteId}")
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
}
