package org.nico.controllers;


import org.nico.business.contract.manager.EnumManager;
import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.impl.SearchFilter;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.GuideBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Controller
public class GuideBookController {

    @Inject
    private GuideBookManager guideBookManager;
    @Inject
    private EnumManager enumManager;


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
}
