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

    /**
     * Display form to add a new route.
     * @param sectorId
     * @param model
     * @param userInSessionId
     * @return routeForm
     */
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

    /**
     * Process to add a new route.
     * @param sectorId
     * @param model
     * @param route
     * @param bindingResult
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
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

    /**
     * Display form to edit a route.
     * @param model
     * @param id
     * @param userInSessionId
     * @return routeEditForm
     */
    @GetMapping("/editRoute/{id}")
    public String displayEditRouteForm(Model model, @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId != null){
            Route editedRoute = routeManager.findRoute(id);
            List<String> quotationList = enumManager.getEnumQuotationStringValues();

            model.addAttribute("quotationList", quotationList);
            model.addAttribute("userInSessionId", userInSessionId);
            model.addAttribute("editedRoute", editedRoute);
            return "routeEditForm";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to edit a route.
     * @param route
     * @param id
     * @param model
     * @param bindingResult
     * @param userInSessionId
     * @return  "/climbingSite/{id}"
     */
    @PostMapping("/editRoute/editRouteProcess/{id}")
    public String editRoute(@Valid Route route, @PathVariable Integer id, Model model, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId != null){
            if (bindingResult.hasErrors()){
                model.addAttribute("memberInSessionId", userInSessionId);
                model.addAttribute("editedRoute", routeManager.findRoute(id));
                return  "routeEditForm";
            } else {
                Integer climbingSiteId = sectorManager.findSector(routeManager.findRoute(id).getSector().getId()).getClimbingSite().getId();

                routeManager.updateRoute(route);
                model.addAttribute("climbingSiteId", climbingSiteId);
                model.addAttribute("userInSessionId", userInSessionId);
                return "redirect:/climbingSite/{climbingSiteId}";
            }
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Method to delete a route.
     * @param id
     * @param model
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
    @GetMapping("/deleteRoute/{id}")
    public String deleteRoute(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            Integer climbingSiteId = routeManager.findRoute(id).getSector().getClimbingSite().getId();

            routeManager.deleteRoute(id);
            model.addAttribute("climbingSiteId", climbingSiteId);
            return "redirect:/climbingSite/{climbingSiteId}";
        } else {
            return "redirect:/login";
        }
    }
}
