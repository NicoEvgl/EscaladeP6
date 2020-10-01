package org.nico.controllers;


import org.nico.business.contract.manager.*;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.GuideBook;
import org.nico.model.beans.GuideBookReservation;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("userInSessionId")
public class PersonalSpaceController {

    @Inject
    private UserManager userManager;
    @Inject
    private ClimbingSiteManager climbingSiteManager;
    @Inject
    private PhotoManager photoManager;
    @Inject
    private EnumManager enumManager;
    @Inject
    private PasswordManager passwordManager;
    @Inject
    private GuideBookManager guideBookManager;
    @Inject
    private GuideBookReservationManager guideBookReservationManager;


    /**
     * Display personal space.
     * @param userInSessionId
     * @param model
     * @return personalSpace
     */
    @GetMapping("/personalSpace/{userInSessionId}")
    public String displayPersonalSpace(@PathVariable@SessionAttribute("userInSessionId")Integer userInSessionId, Model model){

        if (userInSessionId == null){
            return "redirect:/home";
        }
        User registeredUser = userManager.findUser(userInSessionId);
        if (registeredUser.getRole() == Role.ADMIN.getParam()){
            List<User> userList = userManager.findUserList();
            model.addAttribute("memberList", userList);
        }
        List<ClimbingSite> climbingSiteList = climbingSiteManager.findClimbingSiteByUserId(userInSessionId);
        for (ClimbingSite climbingSite : climbingSiteList){
            climbingSite.setPhotoList(photoManager.findPhotoByClimbingSite(climbingSite.getId()));
        }
        List<GuideBook> guideBookList = guideBookManager.findGuideBookByUserId(userInSessionId);
        List<GuideBookReservation> guideBookReservationRequestList = guideBookReservationManager.findGuideBookReservationRequestList(userInSessionId);
        List<GuideBookReservation> userGuideBookReservationList = guideBookReservationManager.findGuideBookReservationListByUserId(userInSessionId);
        User userInSession = userManager.findUser(userInSessionId);

        model.addAttribute("climbingSiteList", climbingSiteList);
        model.addAttribute("guideBookList", guideBookList);
        model.addAttribute("guideBookReservationRequestList", guideBookReservationRequestList);
        model.addAttribute("userGuideBookReservationList", userGuideBookReservationList);
        model.addAttribute("userInSession", userInSession);
        model.addAttribute("userInSessionId", userInSessionId);

        return "personalSpace";
    }

    /**
     * Display form to edit personal infos.
     * @param id
     * @param model
     * @param userInSessionId
     * @return userEditForm
     */
    @GetMapping("/editUser/{id}")
    public String displayUserEditForm(@PathVariable("id") Integer id, Model model, @SessionAttribute("userInSessionId") Integer userInSessionId){
        User registeredUser = userManager.findUser(id);
        User userInSession = userManager.findUser(userInSessionId);
        List<String> roleList = enumManager.getEnumRoleStringValues();

        if(userInSessionId != null && userInSessionId == id){
            model.addAttribute("userInSession", userInSession);
            model.addAttribute("userInSessionId", userInSessionId);
            model.addAttribute("userEdit", registeredUser);
            model.addAttribute("roleList", roleList);
            return "userEditForm";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to edit personal infos.
     * @param user
     * @param id
     * @param model
     * @param userInSessionId
     * @param bindingResult
     * @return "/personalSpace/{id}"
     */
    @PostMapping("/editUser/editUserProcess/{id}")
    public String editUser(@Valid User user, @PathVariable("id") Integer id, Model model, @SessionAttribute("userInSessionId") Integer userInSessionId,BindingResult bindingResult){
        if(userInSessionId != null && userInSessionId == id){
            if (bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors ) {
                    System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
                }
                String str = "Une erreur est survenue. Vérifiez les champs.";
                model.addAttribute("editedUser", user);
                model.addAttribute("errorMessage", str);
                return "userEditForm";
            } else {
                User registeredUser = userManager.findUser(userInSessionId);
                user.setPassword(registeredUser.getPassword());
                userManager.updateUser(user);
                user.setRole((registeredUser.getRole()));
                model.addAttribute("userInSessionId", userInSessionId);
                return "redirect:/personalSpace/{userInSessionId}";
            }
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Display form to edit a password.
     * @param id
     * @param model
     * @param userInSessionId
     * @return passwordEditForm
     */
    @GetMapping("/editPassword/{id}")
    public String displayPasswordEditForm(@PathVariable("id") Integer id, Model model, @SessionAttribute("userInSessionId") Integer userInSessionId){
        if(userInSessionId != null && userInSessionId == id){
            User registeredUser = userManager.findUser(id);
            model.addAttribute("userEdit", registeredUser);
            return "passwordEditForm";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to edit a password.
     * @param user
     * @param id
     * @param model
     * @param userInSessionId
     * @param bindingResult
     * @return "/personalSpace/{id}"
     */
    @PostMapping("/editPassword/editPasswordProcess/{id}")
    public String editPassword(@Valid User user, @PathVariable("id") Integer id, Model model, @SessionAttribute("userInSessionId") Integer userInSessionId, BindingResult bindingResult){
        if(userInSessionId != null && userInSessionId == id){
            if (bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors ) {
                    System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
                }
                String str = "Une erreur est survenue. Vérifiez les champs.";
                model.addAttribute("editedUser", user);
                model.addAttribute("errorMessage", str);
                return "passwordEditForm";
            } else {
                User registeredUser = userManager.findUser(userInSessionId);
                user.setGender(registeredUser.getGender());
                user.setFirstName(registeredUser.getFirstName());
                user.setLastName(registeredUser.getLastName());
                user.setUsername(registeredUser.getUsername());
                user.setEmail(registeredUser.getEmail());
                user.setAddress(registeredUser.getAddress());
                user.setAddress2(registeredUser.getAddress2());
                user.setZip(registeredUser.getZip());
                user.setCity(registeredUser.getCity());
                user.setRole(registeredUser.getRole());

                String hashPassword = passwordManager.hashPassword(user.getPassword());
                user.setPassword(hashPassword);
                userManager.updateUser(user);
                model.addAttribute("userInSessionId", userInSessionId);
                return "redirect:/personalSpace/{userInSessionId}";
            }
        } else {
            return "redirect:/login";
        }
    }
}
