package org.nico.controllers;


import org.nico.model.beans.GuideBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GuideBookController {

    @GetMapping(value = "/guideBook")
    public String displayGuideBookPage(Model model) {
        model.addAttribute("guidebook", new GuideBook());
        return "guideBook";
    }
}
