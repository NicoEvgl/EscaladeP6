package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.SectorDaoImpl;
import org.nico.model.beans.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRowMapper implements RowMapper<Route> {

    @Override
    public Route mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Route route = new Route();
        SectorDaoImpl sectorDao = new SectorDaoImpl();

        route.setId(resultSet.getInt("id"));
        route.setName(resultSet.getString("name"));
        route.setQuotation(resultSet.getString("quotation"));
        route.setHeight(resultSet.getString("height"));
        route.setSector(sectorDao.findSector(resultSet.getInt("sector_id")));

        return route;
    }
}
