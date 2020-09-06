package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.EnumManager;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.Login;
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

        for (ClimbingSite climbingSite : climbingSiteList){
            nameList.add(climbingSite.getName());
        }
        model.addAttribute("regionList", regionList);
        model.addAttribute("cotationList", cotationList);
        model.addAttribute("nameList", nameList);
        model.addAttribute("climbingSiteList", climbingSiteList);

        return "climbingSite";
    }
}
