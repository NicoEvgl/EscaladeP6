package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.EnumManager;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.ClimbingSite;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClimbingSiteController {

    @Inject
    private ClimbingSiteManager climbingSiteManager;

    @Inject
    private EnumManager enumManager;

    @GetMapping(value = "/climbingSiteList")
    public String displayClimbingSiteList(Model model, @SessionAttribute(value = "memberInSessionId", required = false) Integer memberInSessionId) {
        List<ClimbingSite> climbingSiteList = climbingSiteManager.findClimbingSiteList();
        List<String> regionList = enumManager.getEnumRegionStringValues();
        List<String> cotationList = enumManager.getEnumCotationStringValues();
        List<String> nameList = new ArrayList<>();
        List<Integer> nbRoutesList = new ArrayList<>();

        for (ClimbingSite climbingSite : climbingSiteList){
            nameList.add(climbingSite.getName());
        }
        for (int i = 0; i < 101; i++){
            nbRoutesList.add(i);
        }
        model.addAttribute("regionList", regionList);
        model.addAttribute("cotationList", cotationList);
        model.addAttribute("nameList", nameList);
        model.addAttribute("nbRoutesList", nbRoutesList);
        model.addAttribute("searchFilter", new SearchFilter());
        model.addAttribute("climbingSiteList", climbingSiteList);

        return "climbingSiteList";
    }
}
