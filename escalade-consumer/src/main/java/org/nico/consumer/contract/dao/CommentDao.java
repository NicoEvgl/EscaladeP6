package org.nico.consumer.contract.dao;

import org.nico.model.beans.Comment;

public interface CommentDao {
    void createComment(Comment comment);
}
