package org.nico.controllers;


import org.nico.business.contract.manager.EnumManager;
import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.GuideBook;
import org.nico.model.beans.Sector;
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


    /**
     * Display guide book list page.
     * @param model
     * @return guideBookList
     */
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

    /**
     * Display guide book page.
     * @param id
     * @param userInSessionId
     * @param model
     * @return guideBook
     */
    @GetMapping("/guideBook/{id}")
    public String displayGuideBook(@PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, Model model){
        GuideBook guideBook = guideBookManager.findGuideBook(id);

        model.addAttribute("guideBook", guideBook);
        model.addAttribute("userInSessionId", userInSessionId);
        return "guideBook";
    }

    /**
     * Display form to add a new guide book.
     * @param userInSessionId
     * @param model
     * @return guideBookForm
     */
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

    /**
     * Process to add a new guide book.
     * @param newGuideBook
     * @param bindingResult
     * @param model
     * @param userInSessionId
     * @return "/personnalSpace/{id}"
     */
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

    /**
     * Display form to edit a guide book.
     * @param model
     * @param id
     * @param userInSessionId
     * @return guideBookEditForm
     */
    @GetMapping("/editGuideBook/{id}")
    public String displayGuideBookEditForm(Model model, @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        GuideBook editedGuideBook = guideBookManager.findGuideBook(id);
        if (userInSessionId != null && userInSessionId == editedGuideBook.getUser().getId()){
            List<String> regionList = enumManager.getEnumRegionStringValues();

            model.addAttribute("regionList", regionList);
            model.addAttribute("userInSessionId", userInSessionId);
            model.addAttribute("editedGuideBook", editedGuideBook);
            return "guideBookEditForm";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to edit a guide book
     * @param guideBook
     * @param id
     * @param model
     * @param bindingResult
     * @param userInSessionId
     * @return "/guideBookList"
     */
    @PostMapping("/editGuideBook/editGuideBookProcess/{id}")
    public String editGuideBook(@Valid GuideBook guideBook, @PathVariable Integer id, Model model, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        GuideBook editedGuideBook = guideBookManager.findGuideBook(id);
        if (userInSessionId != null && userInSessionId == editedGuideBook.getUser().getId()){
            if (bindingResult.hasErrors()){
                model.addAttribute("userInSessionId", userInSessionId);
                model.addAttribute("editedGuideBook", guideBookManager.findGuideBook(id));
                return  "guideBookEditForm";
            } else {
                guideBook.setBooked(editedGuideBook.isBooked());
                guideBookManager.updateGuideBook(guideBook);
                model.addAttribute("userInSessionId", userInSessionId);
                return "redirect:/guideBookList";
            }
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Method to delete a guide book.
     * @param id
     * @param userInSessionId
     * @param model
     * @return "/guideBookList"
     */
    @GetMapping("/deleteGuideBook/{id}")
    public String deleteGuideBook(@PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId, Model model){
        if (userInSessionId != null){
            guideBookManager.deleteGuideBook(id);
            model.addAttribute("userInSessionId", userInSessionId);
            return "redirect:/guideBookList";
        } else {
            return "redirect:/login";
        }
    }
}
