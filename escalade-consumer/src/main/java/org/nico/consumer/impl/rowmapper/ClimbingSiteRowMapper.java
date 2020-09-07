package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.UserDaoImpl;
import org.nico.model.beans.ClimbingSite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClimbingSiteRowMapper implements RowMapper<ClimbingSite> {

    @Override
    public ClimbingSite mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ClimbingSite climbingSite = new ClimbingSite();
        UserDaoImpl userDao = new UserDaoImpl();

        climbingSite.setId(resultSet.getInt("id"));
        climbingSite.setName(resultSet.getString("name"));
        climbingSite.setRegion(resultSet.getString("region"));
        climbingSite.setClimbingType(resultSet.getString("climbing_type"));
        climbingSite.setRockType(resultSet.getString("rock_type"));
        climbingSite.setHeight(resultSet.getString("height"));
        climbingSite.setNbRoutes(resultSet.getInt("nb_routes"));
        climbingSite.setQuotation(resultSet.getString("quotation"));
        climbingSite.setInfo(resultSet.getString("info"));
        climbingSite.setUser(userDao.findUser(resultSet.getInt("user_id")));

        return climbingSite;

    }
}
