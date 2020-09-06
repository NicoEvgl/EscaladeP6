package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.PhotoDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.model.beans.Photo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;

public class PhotoDaoImpl extends AbstractDao implements PhotoDao {

    @Override
    public void createPhoto(Photo photo) {
        String sql = "INSERT INTO public.photo (name, url, climbingsite_id)"
                + "VALUES (:name, :url, :climbingareaId)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", photo.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("url", photo.getUrl(), Types.VARCHAR);
        mapSqlParameterSource.addValue("climbingareaId", photo.getClimbingSite().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
