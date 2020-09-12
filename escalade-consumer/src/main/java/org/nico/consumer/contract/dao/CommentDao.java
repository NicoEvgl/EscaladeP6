package org.nico.consumer.contract.dao;

import org.nico.model.beans.Comment;

import java.util.List;

public interface CommentDao {
    void createComment(Comment comment);
    List<Comment> findCommentByClimbingSite(Integer id);
}
