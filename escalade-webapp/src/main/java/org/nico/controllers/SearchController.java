package org.nico.controllers;


import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.EnumManager;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.ClimbingSite;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Inject
    private ClimbingSiteManager climbingSiteManager;

    @Inject
    private EnumManager enumManager;


    @PostMapping(value = "/climbingSiteList/search")
    public String displayClimbingSiteListSearchResults(@Valid SearchFilter searchFilter, BindingResult bindingResult, Model model, @SessionAttribute(value = "memberInSessionID", required = false) Integer memberInSessionId){
        if (searchFilter.getName().equals("") && searchFilter.getRegion().equals("") && searchFilter.getNbRoutes() == null && searchFilter.getCotation().equals("")){
            return "redirect:/climbingSiteList";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "Une erreur s'est produite. Veuillez essayer plus tard.");
            return "redirect:/climbingSiteList";
        } else {
            List<ClimbingSite> climbingSiteList = climbingSiteManager.findClimbingSiteSearchRequest(searchFilter.getName(), searchFilter.getRegion(), searchFilter.getNbRoutes(), searchFilter.getCotation());
            List<String> nameList = new ArrayList<>();
            List<String> regionList = enumManager.getEnumRegionStringValues();
            List<Integer> nbRoutesList = new ArrayList<>();
            List<String> cotationList = enumManager.getEnumCotationStringValues();
            List<ClimbingSite> climbingSiteCompleteList = climbingSiteManager.findClimbingSiteList();

            for (ClimbingSite climbingArea : climbingSiteCompleteList){
                nameList.add(climbingArea.getName());
            }
            for (int i = 0; i < 101; i++){
                nbRoutesList.add(i);
            }
            if (climbingSiteList.isEmpty()){
                model.addAttribute("noResults", "Aucun Résultat");
            }

            model.addAttribute("action", "climbingSiteList/search");
            model.addAttribute("climbingSiteList", climbingSiteList);
            model.addAttribute("nameList", nameList);
            model.addAttribute("regionList", regionList);
            model.addAttribute("nbRoutesList", nbRoutesList);
            model.addAttribute("cotationList", cotationList);

            return "climbingSiteList";

        }
    }
}
