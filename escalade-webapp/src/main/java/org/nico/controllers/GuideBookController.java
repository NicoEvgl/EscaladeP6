package org.nico.controllers;


import org.nico.business.contract.manager.EnumManager;
import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.GuideBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class GuideBookController {

    @Inject
    private GuideBookManager guideBookManager;
    @Inject
    private EnumManager enumManager;
    @Inject
    private UserManager userManager;


    @GetMapping(value = "/guideBookList")
    public String displayGuideBookList(Model model) {
        List<GuideBook> guideBookList = guideBookManager.findGuideBookList();
        List<String> nameList = new ArrayList<>();
        List<String> regionList = enumManager.getEnumRegionStringValues();

        for (GuideBook guideBook : guideBookList){
            nameList.add(guideBook.getName());
        }

        model.addAttribute("nameList", nameList);
        model.addAttribute("regionList", regionList);
        model.addAttribute("searchFilter", new SearchFilter());
        model.addAttribute("guideBookList", guideBookList);

        return "guideBookList";
    }

    @GetMapping("/guideBook/{id}")
    public String displayGuideBook(@PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, Model model){
        GuideBook guideBook = guideBookManager.findGuideBook(id);

        model.addAttribute("guideBook", guideBook);
        model.addAttribute("userInSessionId", userInSessionId);
        return "guideBook";
    }

    @GetMapping("/addGuideBook")
    public String displayGuidebookForm(@SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, Model model){
        if (userInSessionId != null){
            List<String> regionList = enumManager.getEnumRegionStringValues();

            model.addAttribute("regionList", regionList);
            model.addAttribute("guideBook", new GuideBook());
            return "guideBookForm";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/addGuideBookProcess")
    public String addGuideBook(@Valid @ModelAttribute("guideBook") GuideBook newGuideBook, BindingResult bindingResult, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            GuideBook registeredGuideBook = guideBookManager.findGuideBookByAttribute("name", newGuideBook.getName());
            if (registeredGuideBook != null){
                model.addAttribute("errorMessage", "Désolé ce site existe déjà");
                return "redirect:/addGuideBook";
            } else {
                if (bindingResult.hasErrors()){
                    model.addAttribute("errorMessage", "Une erreur est survenue. Vérifiez les champs.");
                    return "redirect:/addGuideBook";
                } else {
                    newGuideBook.setUser(userManager.findUser(userInSessionId));
                    newGuideBook.setBooked(false);
                    guideBookManager.createGuideBook(newGuideBook);
                    model.addAttribute("userInSessionId", userInSessionId);
                    return "redirect:/personalSpace/{userInSessionId}";
                }
            }
        } else {
            return "redirect:/login";
        }
    }
}
