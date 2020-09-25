package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.EnumManager;
import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.contract.manager.PhotoManager;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.GuideBook;
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
    @Inject
    private PhotoManager photoManager;
    @Inject
    private GuideBookManager guideBookManager;


    @PostMapping(value = "/climbingSiteList/search")
    public String displayClimbingSiteListSearchResults(@Valid SearchFilter searchFilter, BindingResult bindingResult, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (searchFilter.getName().equals("") && searchFilter.getRegion().equals("") && searchFilter.getNbRoutes() == null && searchFilter.getQuotation().equals("")){
            return "redirect:/climbingSiteList";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "Une erreur s'est produite. Veuillez essayer plus tard.");
            return "redirect:/climbingSiteList";
        } else {
            List<ClimbingSite> climbingSiteList = climbingSiteManager.findClimbingSiteSearchRequest(searchFilter.getName(), searchFilter.getRegion(), searchFilter.getNbRoutes(), searchFilter.getQuotation());
            List<String> nameList = new ArrayList<>();
            List<String> regionList = enumManager.getEnumRegionStringValues();
            List<Integer> nbRoutesList = new ArrayList<>();
            List<String> quotationList = enumManager.getEnumQuotationStringValues();
            List<ClimbingSite> climbingSiteCompleteList = climbingSiteManager.findClimbingSiteList();

            for (ClimbingSite climbingArea : climbingSiteCompleteList){
                nameList.add(climbingArea.getName());
            }
            for (ClimbingSite climbingSite : climbingSiteList){
                climbingSite.setPhotoList(photoManager.findPhotoByClimbingSite(climbingSite.getId()));
            }
            for (int i = 0; i < 900; i++){
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
            model.addAttribute("quotationList", quotationList);

            return "climbingSiteList";
        }
    }

    @PostMapping(value = "/guideBookList/search")
    public String displayGuideBookListSearchResults(@Valid SearchFilter searchFilter, BindingResult bindingResult, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (searchFilter.getName().equals("") && searchFilter.getRegion().equals("")){
            return "redirect:/guideBookList";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "Une erreur s'est produite. Veuillez essayer plus tard.");
            return "redirect:/guideBookList";
        } else {
            List<GuideBook> guideBookList = guideBookManager.findGuideBookSearchRequest(searchFilter.getName(), searchFilter.getRegion());
            List<String> nameList = new ArrayList<>();
            List<String> regionList = enumManager.getEnumRegionStringValues();

            List<GuideBook> guideBookCompleteList = guideBookManager.findGuideBookList();

            for (GuideBook guideBook : guideBookCompleteList){
                nameList.add(guideBook.getName());
            }
            if (guideBookList.isEmpty()){
                model.addAttribute("noResults", "Aucun Résultat");
            }

            model.addAttribute("action", "guideBookList/search");
            model.addAttribute("guideBookList", guideBookList);
            model.addAttribute("nameList", nameList);
            model.addAttribute("regionList", regionList);

            return "guideBookList";
        }
    }
}
