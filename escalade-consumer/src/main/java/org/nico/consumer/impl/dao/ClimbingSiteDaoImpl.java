package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.ClimbingSiteDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.ClimbingSiteRowMapper;
import org.nico.model.beans.ClimbingSite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class ClimbingSiteDaoImpl extends AbstractDao implements ClimbingSiteDao {

    @Override
    public void createClimbingSite(ClimbingSite climbingSite) {
        String sql = "INSERT INTO public.climbingSite (name, region, climbing_type, rock_type, height, nb_routes, cotation,info, user_id)"
                + "VALUES ( :name, :region, :climbingType, :rockType, :height, :nbRoutes, :cotation, :info, :userId)";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", climbingSite.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("region", climbingSite.getRegion(), Types.VARCHAR);
        mapSqlParameterSource.addValue("climbingType", climbingSite.getClimbingType(), Types.VARCHAR);
        mapSqlParameterSource.addValue("rockType", climbingSite.getRockType(), Types.VARCHAR);
        mapSqlParameterSource.addValue("height", climbingSite.getHeight(), Types.VARCHAR);
        mapSqlParameterSource.addValue("nbRoutes", climbingSite.getNbRoutes(), Types.INTEGER);
        mapSqlParameterSource.addValue("cotation", climbingSite.getCotation(), Types.VARCHAR);
        mapSqlParameterSource.addValue("info", climbingSite.getInfo(), Types.VARCHAR);
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
    public List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String cotation) {
        String sql = "SELECT * FROM public.climbingsite " +
                "WHERE climbingsite.name = :name OR climbingsite.region = :region OR climbingsite.nb_routes = :nbRoutes OR climbingsite.cotation = :cotation";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", name, Types.VARCHAR);
        mapSqlParameterSource.addValue("region", region, Types.VARCHAR);
        mapSqlParameterSource.addValue("nbRoutes", nbRoutes, Types.INTEGER);
        mapSqlParameterSource.addValue("cotation", cotation, Types.VARCHAR);

        ClimbingSiteRowMapper climbingSiteRowMapper = new ClimbingSiteRowMapper();
        List<ClimbingSite> climbingSiteList = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, climbingSiteRowMapper );

        return climbingSiteList;
    }


}
