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


    /**
     * Display form to add a comment.
     * @param climbingSiteId
     * @param model
     * @param userInSessionId
     * @return commentForm
     */
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

    /**
     * Process to add a comment
     * @param climbingSiteId
     * @param model
     * @param comment
     * @param bindingResult
     * @param userInSessionId
     * @return "/climningSite/{id}"
     */
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

    /**
     * Display form to edit a comment.
     * @param model
     * @param id
     * @param userInSessionId
     * @return commentEditForm
     */
    @GetMapping("/editComment/{id}")
    public String displayCommentEditForm(Model model, @PathVariable Integer id, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId != null){

            Comment editedComment = commentManager.findComment(id);
            model.addAttribute("userInSessionId", userInSessionId);
            model.addAttribute("editedComment", editedComment);
            return "commentEditForm";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Process to edit a comment.
     * @param comment
     * @param id
     * @param model
     * @param bindingResult
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
    @PostMapping("/editComment/editCommentProcess/{id}")
    public String editComment(@Valid Comment comment, @PathVariable Integer id, Model model, BindingResult bindingResult, @SessionAttribute(value = "userInSessionId", required = false)Integer userInSessionId){
        if (userInSessionId != null){
            if (bindingResult.hasErrors()){
                model.addAttribute("userInSessionId", userInSessionId);
                model.addAttribute("editedComment", commentManager.findComment(id));
                return  "commentEditForm";
            } else {
                model.addAttribute("climbingSiteId", commentManager.findComment(id).getClimbingSite().getId());
                model.addAttribute("userInSessionId", userInSessionId);
                comment.setUpdateDate (new Timestamp(System.currentTimeMillis()));
                commentManager.updateComment(comment);
                return "redirect:/climbingSite/{climbingSiteId}";
            }
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Method to delete a comment
     * @param id
     * @param model
     * @param userInSessionId
     * @return "/climbingSite/{id}"
     */
    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Integer id, Model model, @SessionAttribute(value = "userInSessionId", required = false) Integer userInSessionId){
        if (userInSessionId != null){
            model.addAttribute("climbId", commentManager.findComment(id).getClimbingSite().getId());
            model.addAttribute("userInSessionId", userInSessionId);
            commentManager.deleteComment(id);
            return "redirect:/climbingSite/{climbingSiteId}";
        } else {
            return "redirect:/login";
        }
    }

}
