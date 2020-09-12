package org.nico.business.contract.manager;

import org.nico.model.beans.Comment;

import java.util.List;

public interface CommentManager {
    void createComment(Comment comment);
    List<Comment> findCommentByClimbingSite(Integer id);
}
