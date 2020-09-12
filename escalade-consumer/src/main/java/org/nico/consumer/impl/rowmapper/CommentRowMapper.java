package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.ClimbingSiteDaoImpl;
import org.nico.consumer.impl.dao.UserDaoImpl;
import org.nico.model.beans.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Comment comment = new Comment();
        UserDaoImpl userDao = new UserDaoImpl();
        ClimbingSiteDaoImpl climbingSiteDao = new ClimbingSiteDaoImpl();

        comment.setId(resultSet.getInt("id"));
        comment.setCommentText(resultSet.getString("comment_text"));
        comment.setCreationDate(resultSet.getTimestamp("creation_date"));
        comment.setUpdateDate(resultSet.getTimestamp("update_date"));
        comment.setUser(userDao.findUser(resultSet.getInt("user_id")));
        comment.setClimbingSite(climbingSiteDao.findClimbingSite(resultSet.getInt("climbingSite_id")));

        return comment;
    }
}
