package org.nico.controllers;

import org.nico.business.contract.manager.EnumManager;
import org.nico.business.contract.manager.RouteManager;
import org.nico.business.contract.manager.SectorManager;
import org.nico.model.beans.Route;
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
public class RouteController {

    @Inject
    private RouteManager routeManager;
    @Inject
    private SectorManager sectorManager;
    @Inject
    private EnumManager enumManager;

    @GetMapping("/addRoute/{sectorId}")
    public String displayRouteForm(@PathVariable Integer sectorId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            Route route = new Route();
            route.setSector(sectorManager.findSector(sectorId));
            List<String> quotationList = enumManager.getEnumQuotationStringValues();

            model.addAttribute("quotationList", quotationList);
            model.addAttribute("route", route);
            model.addAttribute("sectorId", sectorId);
            return "routeForm";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/addRoute/addRouteProcess/{sectorId}")
    public String addRoute(@PathVariable Integer sectorId, Model model, @Valid Route route, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){

        if (userInSessionId != null){
            if (bindingResult.hasErrors()){
                model.addAttribute("errorMessage", "Une erreur est survenue. Vérifiez les champs.");
                return "routeForm";
            } else {
                routeManager.createRoute(route);
                Integer climbingSiteId = sectorManager.findSector(sectorId).getClimbingSite().getId();

                model.addAttribute("climbingSiteId", climbingSiteId);
                return "redirect:/climbingSite/{climbingSiteId}";
            }
        } else {
            return "redirect:/login";
        }
    }
}
