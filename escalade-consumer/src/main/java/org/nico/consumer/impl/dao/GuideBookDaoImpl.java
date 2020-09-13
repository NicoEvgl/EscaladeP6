package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.GuideBookDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.ClimbingSiteRowMapper;
import org.nico.consumer.impl.rowmapper.GuideBookRowMapper;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.GuideBook;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class GuideBookDaoImpl extends AbstractDao implements GuideBookDao {

    @Override
    public void createGuideBook(GuideBook guideBook) {
        String sql = "INSERT INTO public.guidebook (name, description,region, release_date, is_booked, user_id)"
                + "VALUES (:name, :description, :region, :releaseDate, :isBooked, :userId)";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", guideBook.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("description", guideBook.getDescription(), Types.VARCHAR);
        mapSqlParameterSource.addValue("region", guideBook.getRegion(), Types.VARCHAR);
        mapSqlParameterSource.addValue("releaseDate", guideBook.getReleaseDate(), Types.DATE);
        mapSqlParameterSource.addValue("isBooked", guideBook.isBooked(), Types.BOOLEAN);
        mapSqlParameterSource.addValue("userId", guideBook.getUser().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

    }

    @Override
    public List<GuideBook> findGuideBookList() {
        String sql = "SELECT * FROM public.guidebook";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());

        GuideBookRowMapper guideBookRowMapper = new GuideBookRowMapper();
        List<GuideBook> guideBookList = jdbcTemplate.query(sql, guideBookRowMapper);

        return guideBookList;
    }

    @Override
    public List<GuideBook> findGuideBookSearchRequest(String name, String region) {
        String sql = "SELECT distinct guidebook. * FROM public.guidebook " +
                "WHERE guidebook.name = :name OR guidebook.region = :region";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", name, Types.VARCHAR);
        mapSqlParameterSource.addValue("region", region, Types.VARCHAR);

        GuideBookRowMapper guideBookRowMapper = new GuideBookRowMapper();
        List<GuideBook> guideBookList = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, guideBookRowMapper );

        return guideBookList;
    }

    @Override
    public GuideBook findGuideBook(Integer id) {
        String sql = "SELECT * FROM public.guidebook WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        GuideBookRowMapper guideBookRowMapper = new GuideBookRowMapper();
        GuideBook guideBook = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, guideBookRowMapper);

        return guideBook;
    }

    @Override
    public GuideBook findGuideBookByAttribute(String attribute, Object attributeValue) {
        String sql = "SELECT * FROM public.guidebook WHERE "+attribute+" = :"+attribute+"";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue(attribute, attributeValue);
        GuideBook guideBook = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, new GuideBookRowMapper());

        return guideBook;
    }
}
