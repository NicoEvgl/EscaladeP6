package org.nico.controllers;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.CommentManager;
import org.nico.business.contract.manager.UserManager;
import org.nico.model.beans.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.validation.Valid;
import java.sql.Timestamp;


@Controller
public class CommentController {

    @Inject
    private CommentManager commentManager;
    @Inject
    private ClimbingSiteManager climbingSiteManager;
    @Inject
    private UserManager userManager;


    @GetMapping("/addComment/{climbingSiteId}")
    public String displayCommentForm(@PathVariable Integer climbingSiteId, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            Comment comment = new Comment();
            model.addAttribute("comment", comment);
            model.addAttribute("climbingSiteId", climbingSiteId);
            return "commentForm";

        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/addComment/addCommentProcess/{climbingSiteId}")
    public String saveComment(@PathVariable Integer climbingSiteId, Model model, @Valid Comment comment, BindingResult bindingResult,
                              @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){

        if (userInSessionId != null){
            if (bindingResult.hasErrors()){
                model.addAttribute("errorMessage", "Une erreur est survenue. Vérifiez les champs.");
                return "commentForm";
            } else {
                comment.setClimbingSite(climbingSiteManager.findClimbingSite(climbingSiteId));
                comment.setUser(userManager.findUser(userInSessionId));
                comment.setCreationDate (new Timestamp(System.currentTimeMillis()));

                commentManager.createComment(comment);
                model.addAttribute("climbingSiteId", climbingSiteId);
                return "redirect:/climbingSite/{climbingSiteId}";
            }
        } else {
            return "redirect:/login";
        }
    }


}
