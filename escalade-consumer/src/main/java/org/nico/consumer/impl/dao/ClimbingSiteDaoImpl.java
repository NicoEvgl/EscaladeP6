package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.ClimbingSiteDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.ClimbingSiteRowMapper;
import org.nico.consumer.impl.rowmapper.UserRowMapper;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class ClimbingSiteDaoImpl extends AbstractDao implements ClimbingSiteDao {

    @Override
    public void createClimbingSite(ClimbingSite climbingSite) {
        String sql = "INSERT INTO public.climbingSite (name, region, climbing_type, rock_type, height, nb_routes, quotation, info, is_certified, user_id)"
                + "VALUES (:name, :region, :climbingType, :rockType, :height, :nbRoutes, :quotation, :info, :isCertified, :userId)";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", climbingSite.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("region", climbingSite.getRegion(), Types.VARCHAR);
        mapSqlParameterSource.addValue("climbingType", climbingSite.getClimbingType(), Types.VARCHAR);
        mapSqlParameterSource.addValue("rockType", climbingSite.getRockType(), Types.VARCHAR);
        mapSqlParameterSource.addValue("height", climbingSite.getHeight(), Types.VARCHAR);
        mapSqlParameterSource.addValue("nbRoutes", climbingSite.getNbRoutes(), Types.INTEGER);
        mapSqlParameterSource.addValue("quotation", climbingSite.getQuotation(), Types.VARCHAR);
        mapSqlParameterSource.addValue("info", climbingSite.getInfo(), Types.VARCHAR);
        mapSqlParameterSource.addValue("isCertified", climbingSite.isCertified(), Types.BOOLEAN);
        mapSqlParameterSource.addValue("userId", climbingSite.getUser().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<ClimbingSite> findClimbingSiteList(){
        String sql = "SELECT * FROM public.climbingsite ORDER BY name";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        ClimbingSiteRowMapper climbingSiteRowMapper = new ClimbingSiteRowMapper();

        List<ClimbingSite> climbingSiteList = jdbcTemplate.query(sql, climbingSiteRowMapper);

        return climbingSiteList;
    }

    @Override
    public ClimbingSite findClimbingSite(Integer id) {
        String sql = "SELECT * FROM public.climbingsite WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        ClimbingSiteRowMapper climbingSiteRowMapper = new ClimbingSiteRowMapper();
        ClimbingSite climbingSite = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, climbingSiteRowMapper);

        return climbingSite;
    }

    @Override
    public List<ClimbingSite> findClimbingSiteByUserId(Integer id) {
        String sql = "SELECT * FROM public.climbingsite WHERE user_id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        ClimbingSiteRowMapper climbingSiteRowMapper = new ClimbingSiteRowMapper();
        List<ClimbingSite> climbingSiteList = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, climbingSiteRowMapper);

        return climbingSiteList;
    }

    @Override
    public ClimbingSite findClimbingSiteByAttribute(String attribute, Object attributeValue) {
        String sql = "SELECT * FROM public.climbingsite WHERE "+attribute+" = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        try{
            ClimbingSite climbingSite = jdbcTemplate.queryForObject(sql, new ClimbingSiteRowMapper(), attributeValue);
            return climbingSite;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String quotation) {
        String sql = "SELECT distinct climbingsite. * FROM public.climbingsite " +
                "WHERE climbingsite.name = :name OR climbingsite.region = :region OR climbingsite.nb_routes = :nbRoutes OR climbingsite.quotation = :quotation";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", name, Types.VARCHAR);
        mapSqlParameterSource.addValue("region", region, Types.VARCHAR);
        mapSqlParameterSource.addValue("nbRoutes", nbRoutes, Types.INTEGER);
        mapSqlParameterSource.addValue("quotation", quotation, Types.VARCHAR);

        ClimbingSiteRowMapper climbingSiteRowMapper = new ClimbingSiteRowMapper();
        List<ClimbingSite> climbingSiteList = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, climbingSiteRowMapper );

        return climbingSiteList;
    }

    @Override
    public void updateClimbingSite(ClimbingSite climbingSite) {
        String sql = "UPDATE public.climbingsite SET "
                + "name = :name, "
                + "region = :region, "
                + "climbing_type = :climbingType, "
                + "rock_type = :rockType, "
                + "height = :height, "
                + "nb_routes = :nbRoutes, "
                + "quotation = :quotation, "
                + "info = :info, "
                + "is_certified = :isCertified, "
                + "user_id = :userId "
                + "WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", climbingSite.getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("name", climbingSite.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("region", climbingSite.getRegion(), Types.VARCHAR);
        mapSqlParameterSource.addValue("climbingType", climbingSite.getClimbingType(), Types.VARCHAR);
        mapSqlParameterSource.addValue("rockType", climbingSite.getRockType(), Types.VARCHAR);
        mapSqlParameterSource.addValue("height", climbingSite.getHeight(), Types.VARCHAR);
        mapSqlParameterSource.addValue("nbRoutes", climbingSite.getNbRoutes(), Types.INTEGER);
        mapSqlParameterSource.addValue("quotation", climbingSite.getQuotation(), Types.VARCHAR);
        mapSqlParameterSource.addValue("info", climbingSite.getInfo(), Types.VARCHAR);
        mapSqlParameterSource.addValue("isCertified", climbingSite.isCertified(), Types.BOOLEAN);
        mapSqlParameterSource.addValue("userId", climbingSite.getUser().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void deleteClimbingSite(Integer id) {
        String sql = "DELETE FROM public.climbingsite WHERE id = :id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void addTag(Integer id) {
        String sql = "UPDATE public.climbingsite "
                + "SET is_certified = true "
                + "WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void deleteTag(Integer id) {
        String sql = "UPDATE public.climbingsite "
                + "SET is_certified = false "
                + "WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
