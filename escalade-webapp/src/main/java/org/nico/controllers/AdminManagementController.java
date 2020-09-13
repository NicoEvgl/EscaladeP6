package org.nico.controllers;


import org.nico.business.contract.manager.EnumManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.model.beans.User;
import org.nico.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminManagementController {

    @Inject
    private UserManager userManager;
    @Inject
    private EnumManager enumManager;

    @GetMapping("/userList")
    public String displayMemberList(@SessionAttribute(value = "userInSessionId" , required = false) Integer userInSessionId, Model model){
        User adminUser = userManager.findUser(userInSessionId);
        if (!adminUser.getRole().equals(Role.ADMIN.getParam())){
            return "redirect:/home";
        }
        List<User> userList = new ArrayList<>();
        if (adminUser.getRole().equals(Role.ADMIN.getParam())){
            userList = userManager.findUserList();
        }
        model.addAttribute("userList", userList);
        return "userListPage";
    }

    @GetMapping("/editUserRole/{id}")
    public String displayUserAdminEditForm(@PathVariable("id") Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){

        User adminUser = userManager.findUser(userInSessionId);
        if (!adminUser.getRole().equals(Role.ADMIN.getParam())){
            return "redirect:/home";
        }
        User editedUser = userManager.findUser(id);
        model.addAttribute("roleList", enumManager.getEnumRoleStringValues());
        model.addAttribute("editedUser", editedUser);
        return "userAdminEditForm";
    }

    @PostMapping("/editUserRole/editUserRoleProcess/{id}")
    public String editUserRole (@Valid User user, @PathVariable("id") Integer id, Model model, @SessionAttribute(value = "userInSessionId" , required = false) Integer userInSessionId, BindingResult bindingResult){
        User adminUser = userManager.findUser(userInSessionId);
        if (!adminUser.getRole().equals(Role.ADMIN.getParam())){
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()){
            System.out.println("erreur binding");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            String str = "Une erreur est survenue. Vérifiez les champs.";
            model.addAttribute("editedUser", user);
            model.addAttribute("errorMessage", str);
            return "userAdminEditForm";
        } else {
            User registeredUser = userManager.findUser(id);

            user.setGender(registeredUser.getGender());
            user.setFirstName(registeredUser.getFirstName());
            user.setLastName(registeredUser.getLastName());
            user.setUsername(registeredUser.getUsername());
            user.setEmail(registeredUser.getEmail());
            user.setPassword(registeredUser.getPassword());

            userManager.updateUser(user);
            model.addAttribute("userInSessionId", userInSessionId);
            return "redirect:/userList";
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteMember(@PathVariable Integer id, @SessionAttribute(value = "userInSessionId" , required = false) Integer userInSessionId){
        User adminUser = userManager.findUser(userInSessionId);
        if (!adminUser.getRole().equals(Role.ADMIN.getParam())){
            return "redirect:/home";
        }
        userManager.deleteUser(id);

        return "redirect:/userListPage";
    }
}
