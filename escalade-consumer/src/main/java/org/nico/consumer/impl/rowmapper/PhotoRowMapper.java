package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.ClimbingSiteDaoImpl;
import org.nico.model.beans.Photo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoRowMapper implements RowMapper<Photo> {

    @Override
    public Photo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Photo photo = new Photo();
        ClimbingSiteDaoImpl climbingSiteDao = new ClimbingSiteDaoImpl();

        photo.setId(resultSet.getInt("id"));
        photo.setName(resultSet.getString("name"));
        photo.setUrl(resultSet.getString("url"));
        photo.setClimbingSite(climbingSiteDao.findClimbingSite(resultSet.getInt("climbingsite_id")));
        
        return photo;
    }
}
