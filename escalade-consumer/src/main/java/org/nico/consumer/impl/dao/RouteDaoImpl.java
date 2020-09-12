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
    public List<Route> findRouteBySectorId(Integer id) {
        String sql = "SELECT * FROM public.route WHERE sector_id = " + id + " ORDER BY id";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        RouteRowMapper routeRowMapper = new RouteRowMapper();
        List<Route> routeList = jdbcTemplate.query(sql, routeRowMapper);

        return routeList;
    }
}
