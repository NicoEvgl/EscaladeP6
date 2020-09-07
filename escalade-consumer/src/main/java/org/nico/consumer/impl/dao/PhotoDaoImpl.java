package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.PhotoDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.PhotoRowMapper;
import org.nico.model.beans.Photo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

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

    @Override
    public List<Photo> findPhotoList() {
        String sql = "SELECT * FROM public.photo ORDER BY id";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        PhotoRowMapper photoRowMapper = new PhotoRowMapper();

        List<Photo> photoList = jdbcTemplate.query(sql, photoRowMapper);

        return photoList;
    }

    @Override
    public Photo findPhoto(Integer id) {
        String sql = "SELECT * FROM public.photo WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        PhotoRowMapper photoRowMapper = new PhotoRowMapper();
        Photo photo = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, photoRowMapper);

        return photo;
    }

    @Override
    public List<Photo> findPhotoByClimbingSiteId(Integer id) {
        String sql = "SELECT * FROM public.photo WHERE climbingsite_id = "+id;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        PhotoRowMapper photoRowMapper = new PhotoRowMapper();

        List<Photo> photoList = jdbcTemplate.query(sql, photoRowMapper);

        return photoList;
    }




}
