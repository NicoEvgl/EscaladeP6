package org.nico.controllers;

import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.contract.manager.GuideBookReservationManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.model.beans.GuideBookReservation;
import org.nico.model.enums.ReservationStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class GuideBookReservationController {

    @Inject
    private GuideBookReservationManager guideBookReservationManager;
    @Inject
    private GuideBookManager guideBookManager;
    @Inject
    private UserManager userManager;

    @GetMapping("/guideBookReservation/{id}")
    public String displayGuideBookReservationForm(@PathVariable Integer id, @SessionAttribute("userInSessionId")Integer userInSessionId, Model model){
        if (userInSessionId == null){
            return "redirect:/login";
        }
        GuideBookReservation registeredReservation = guideBookReservationManager.findGuideBookReservationByGuideBookAndUserId(id, userInSessionId);
        String reservationStatus = new String();
        if (registeredReservation != null){
            reservationStatus = registeredReservation.getReservationStatus();
            model.addAttribute("reservationStatus", reservationStatus);
            model.addAttribute("guideBookId", id);
            model.addAttribute("userInSessionId", userInSessionId);
            model.addAttribute("errorMessage", "Une demande de réservation pour ce topo a déjà était faite");

            return "personalSpace";
        } else {
            reservationStatus = "null";
        }
        model.addAttribute("reservationStatus", reservationStatus);
        model.addAttribute("guideBookId", id);
        model.addAttribute("guideBookReservation", new GuideBookReservation());

        return "guideBookReservationForm";
    }

    @PostMapping("/guideBookReservation/guideBookReservationProcess/{id}")
    public String doReservation(@Valid @ModelAttribute("guideBookReservation")GuideBookReservation newGuideBookReservation, @PathVariable Integer id, BindingResult bindingResult, Model model,
                                @SessionAttribute("userInSessionId")Integer userInSessionId){
        if (userInSessionId == null){
            return "redirect:/login";
        } else {
            if (bindingResult.hasErrors()){
                model.addAttribute("guideBookId", id);
                model.addAttribute("userInSessionId", userInSessionId);
                return "guideBookReservationForm";
            } else {
                newGuideBookReservation.setReservationStatus(ReservationStatus.PENDING.getStatusValue());
                newGuideBookReservation.setGuideBook(guideBookManager.findGuideBook(id));
                newGuideBookReservation.setUser(userManager.findUser(userInSessionId));
                guideBookReservationManager.createGuideBookReservation(newGuideBookReservation);
                return "redirect:/guideBookList";
            }
        }
    }
}
