package org.nico.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.contract.manager.GuideBookReservationManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.model.beans.GuideBook;
import org.nico.model.beans.GuideBookReservation;
import org.nico.model.enums.ReservationStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class GuideBookReservationController {

    private static final Logger logger = LogManager.getLogger(GuideBookReservationController.class);


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
        logger.debug(reservationStatus);
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

    @GetMapping("/cancelGuideBookReservation/{id}")
    public String cancelReservation(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            GuideBookReservation registeredReservation = guideBookReservationManager.findGuideBookReservationById(id);
            registeredReservation.setReservationStatus(ReservationStatus.CANCELLED.getStatusValue());
            GuideBook rentedGuideBook = registeredReservation.getGuideBook();
            rentedGuideBook.setBooked(false);
            guideBookReservationManager.updateGuideBookReservation(registeredReservation);
            guideBookManager.updateGuideBook(rentedGuideBook);

            model.addAttribute("userInSessionId", userInSessionId);

            return "redirect:/personalSpace/{userInSessionId}";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/acceptGuideBookReservation/{id}")
    public String acceptReservation(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            GuideBookReservation registeredReservation = guideBookReservationManager.findGuideBookReservationById(id);
            registeredReservation.setReservationStatus(ReservationStatus.ACCEPTED.getStatusValue());
            GuideBook rentedGuideBook = registeredReservation.getGuideBook();
            rentedGuideBook.setBooked(true);
            guideBookReservationManager.updateGuideBookReservation(registeredReservation);
            guideBookManager.updateGuideBook(rentedGuideBook);

            model.addAttribute("userInSessionId", userInSessionId);

            return "redirect:/personalSpace/{userInSessionId}";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/refuseGuideBookReservation/{id}")
    public String refuseReservation(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            GuideBookReservation registeredReservation = guideBookReservationManager.findGuideBookReservationById(id);
            registeredReservation.setReservationStatus(ReservationStatus.REFUSED.getStatusValue());
            guideBookReservationManager.updateGuideBookReservation(registeredReservation);

            model.addAttribute("userInSessionId", userInSessionId);

            String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            System.out.println(uri);
            return "redirect:/personalSpace/{userInSessionId}";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/closeGuideBookReservation/{id}")
    public String closeReservation(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            GuideBookReservation registeredReservation = guideBookReservationManager.findGuideBookReservationById(id);
            registeredReservation.setReservationStatus(ReservationStatus.CLOSED.getStatusValue());
            GuideBook rentedGuideBook = registeredReservation.getGuideBook();
            rentedGuideBook.setBooked(false);
            guideBookReservationManager.updateGuideBookReservation(registeredReservation);
            guideBookManager.updateGuideBook(rentedGuideBook);
            model.addAttribute("userInSessionId", userInSessionId);

            return "redirect:/personalSpace/{userInSessionId}";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/deleteGuideBookReservation/{id}")
    public String deleteReservation(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            model.addAttribute("userInSessionId", userInSessionId);
            guideBookReservationManager.deleteGuideBookReservation(id);
            return "redirect:/personalSpace/{userInSessionId}";
        } else {
            return "redirect:/login";
        }
    }
}
