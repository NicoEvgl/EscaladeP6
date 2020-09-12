package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.ClimbingSiteDaoImpl;
import org.nico.model.beans.Sector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorRowMapper implements RowMapper<Sector> {
    @Override
    public Sector mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Sector sector = new Sector();
        ClimbingSiteDaoImpl climbingSiteDao = new ClimbingSiteDaoImpl();

        sector.setId(resultSet.getInt("id"));
        sector.setName(resultSet.getString("name"));
        sector.setDescription(resultSet.getString("description"));
        sector.setClimbingSite(climbingSiteDao.findClimbingSite(resultSet.getInt("climbingSite_id")));

        return sector;
    }
}
