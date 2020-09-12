package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.RouteDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.RouteRowMapper;
import org.nico.model.beans.Route;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class RouteDaoImpl extends AbstractDao implements RouteDao {

    @Override
    public void createRoute(Route route) {
        String sql = "INSERT INTO public.route (name, quotation, height, sector_id)"
                + "VALUES (:name, :quotation, :height, :sectorId)";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("name", route.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("quotation", route.getQuotation(), Types.VARCHAR);
        mapSqlParameterSource.addValue("height", route.getHeight(), Types.VARCHAR);
        mapSqlParameterSource.addValue("sectorId", route.getSector().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<Route> findRouteBySector(Integer id) {
        String sql = "SELECT * FROM public.route WHERE sector_id = " + id + " ORDER BY id";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        RouteRowMapper routeRowMapper = new RouteRowMapper();
        List<Route> routeList = jdbcTemplate.query(sql, routeRowMapper);

        return routeList;
    }

    @Override
    public Route findRoute(Integer id) {
        String sql = "SELECT * FROM public.route WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        RouteRowMapper routeRowMapper = new RouteRowMapper();
        Route route = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, routeRowMapper);

        return route;
    }

    @Override
    public void updateRoute(Route route) {
        String sql = "UPDATE public.route SET "
                + "name = :name, "
                + "quotation = :quotation, "
                + "height = :height, "
                + "sector_id = :sectorId "
                + "WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", route.getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("name", route.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("quotation", route.getQuotation(), Types.VARCHAR);
        mapSqlParameterSource.addValue("height", route.getHeight(), Types.VARCHAR);
        mapSqlParameterSource.addValue("sectorId", route.getSector().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void deleteRoute(Integer id) {
        String sql = "DELETE FROM public.route WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
