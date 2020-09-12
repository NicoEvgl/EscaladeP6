package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.CommentDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.model.beans.Comment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;

public class CommentDaoImpl extends AbstractDao implements CommentDao {

    @Override
    public void createComment(Comment comment) {
        String sql = "INSERT INTO public.comment (comment_text, creation_date, update_date, user_id, climbingsite_id)"
                + "VALUES (:description, :creationDate, :updateDate, :userId, :climbingSiteId)";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("commentText", comment.getCommentText(), Types.VARCHAR);
        mapSqlParameterSource.addValue("creationDate", comment.getCreationDate(), Types.TIMESTAMP);
        mapSqlParameterSource.addValue("updateDate", comment.getUpdateDate(), Types.TIMESTAMP);
        mapSqlParameterSource.addValue("userId", comment.getUser().getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("climbingSiteId", comment.getClimbingSite().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
